package com.godmonth.topia.basic.patroller;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.Validate;
import org.apache.commons.lang3.exception.ContextedRuntimeException;
import org.springframework.beans.factory.InitializingBean;

import com.google.common.base.Stopwatch;
import com.godmonth.topia.basic.competition.LockCallback;
import com.godmonth.topia.basic.competition.LockTemplate2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MutexPatroller implements InitializingBean {

	private LockTemplate2 lockTemplate;

	private Object target;

	private String method;

	private String lockKey;
	private Method reflectMethod;

	@Override
	public void afterPropertiesSet() throws Exception {
		reflectMethod = target.getClass().getMethod(method);
	}

	public void patrol() {
		Stopwatch stopwatch = Stopwatch.createStarted();
		try {
			log.info("patrol begin:{}", lockKey);
			lockTemplate.execute(lockKey, new LockCallback<Void>() {

				@Override
				public Void locked() {
					try {
						reflectMethod.invoke(target);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						throw new ContextedRuntimeException(e);
					}
					return null;
				}
			});
		} finally {
			stopwatch.stop();
			log.info("patrol end:{}, cost:{}", lockKey, stopwatch);
		}
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public void setLockTemplate(LockTemplate2 lockTemplate) {
		this.lockTemplate = lockTemplate;
	}

	public void setLockKey(String lockKey) {
		Validate.notBlank(lockKey, "lockKey is blank");
		this.lockKey = lockKey;
	}

}
