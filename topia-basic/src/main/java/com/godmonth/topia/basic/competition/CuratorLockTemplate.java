package com.godmonth.topia.basic.competition;

import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CuratorLockTemplate implements LockTemplate2 {

	private CuratorFramework curatorFramework;

	private String pathPrefix;

	/**
	 * 分布式锁,相同的或者不同场景共用一个lockKey,相同的场景执行无冷却时间,不同的场景无冷却时间
	 *
	 * @param lockKey      既被用作锁名，也被用作锁信息名
	 * @param lockCallback
	 * @return
	 */
	public <T> T execute(String lockKey, LockCallback<T> lockCallback) {
		String lockKeyPath = lockKey.startsWith("/") ? pathPrefix + lockKey : pathPrefix + "/" + lockKey;
		InterProcessMutex lock = new InterProcessMutex(curatorFramework, lockKeyPath);
		try {
			boolean tryLock = false;
			try {
				log.trace("try locking:{}", lockKeyPath);
				tryLock = lock.acquire(0, TimeUnit.MILLISECONDS);
				log.trace("locked {}:{}", tryLock, lockKeyPath);
			} catch (Exception e) {
				throw new ContextedRuntimeException(e);
			}
			if (tryLock) {
				return lockCallback.locked();
			} else {
				return null;
			}
		} finally {
			try {
				if (lock.isOwnedByCurrentThread()) {
					log.trace("unlocking:{}", lockKeyPath);
					lock.release();
					log.trace("unlocked:{}", lockKeyPath);
				}
			} catch (Exception e) {
				log.error("unlock fail", e);
			}
		}
	}

	public void setCuratorFramework(@NotNull CuratorFramework curatorFramework) {
		this.curatorFramework = curatorFramework;
	}

	public void setPathPrefix(@NotNull String pathPrefix) {
		this.pathPrefix = pathPrefix;
	}

}
