package com.godmonth.topia.basic.curator;

import java.io.IOException;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.zookeeper.data.Stat;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 无缓存
 * 
 * @author godmonth
 *
 * @param <T>
 */
public class ZookeeperSingletonRepo<T> implements SingletonRepo<T> {
	private ObjectMapper objectMapper;

	private CuratorFramework curatorFramework;

	private String path;

	private NodeCache nodeCache;

	private Class<T> clazz;

	public void init() throws Exception {
		nodeCache = new NodeCache(curatorFramework, path);
		nodeCache.start();
	}

	public void close() throws IOException {
		nodeCache.close();
	}

	@Override
	public void delete() {
		try {
			curatorFramework.delete().forPath(path);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	@Override
	public void put(T input) {
		try {
			byte[] writeValueAsBytes = null;
			if (input != null) {
				writeValueAsBytes = objectMapper.writeValueAsBytes(input);
			}

			Stat stat = curatorFramework.checkExists().forPath(path);
			if (stat == null) {
				curatorFramework.create().forPath(path, writeValueAsBytes);
			} else {
				curatorFramework.setData().forPath(path, writeValueAsBytes);
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	@Override
	public T get() {
		try {
			Stat stat = curatorFramework.checkExists().forPath(path);
			if (stat == null) {
				return null;
			}
			byte[] data = curatorFramework.getData().forPath(path);
			if (ArrayUtils.isEmpty(data)) {
				return null;
			}
			return objectMapper.readValue(data, clazz);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			throw new ContextedRuntimeException(e);
		}
	}

	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	public void setCuratorFramework(CuratorFramework curatorFramework) {
		this.curatorFramework = curatorFramework;
	}

	public void setClazz(Class<T> clazz) {
		this.clazz = clazz;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
