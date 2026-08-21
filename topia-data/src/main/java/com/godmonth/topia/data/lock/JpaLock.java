package com.godmonth.topia.data.lock;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import jakarta.persistence.PersistenceContext;

import org.apache.commons.lang3.Validate;

import com.godmonth.topia.data.model.MutableModel;

public class JpaLock implements LockOrder<String> {
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void lock(Class<? extends MutableModel> entityClass, String id,
			long dataVersion) {
		MutableModel mutableModel = entityManager.find(entityClass, id);
		Validate.notNull(mutableModel);
		Validate.isTrue(mutableModel.getDataVersion() == dataVersion);
		entityManager.lock(mutableModel, LockModeType.PESSIMISTIC_WRITE);
	}

}
