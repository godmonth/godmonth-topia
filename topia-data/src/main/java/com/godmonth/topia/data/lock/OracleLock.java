package com.godmonth.topia.data.lock;

import java.io.Serializable;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.Table;

import com.godmonth.topia.data.model.MutableModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OracleLock<ID extends Serializable> implements LockOrder<String> {

	@PersistenceContext
	private EntityManager entityManager;

	private int timeout = 5;
	private String idColumn = "id";

	private String lockSqlTemplate = "select %s from %s where %s = ? and data_version = ? for update wait %d";

	public void lock(Class<? extends MutableModel> entityClass, String id,
			long dataVersion) {
		Table declaredAnnotation = entityClass.getAnnotation(Table.class);
		String sql = String.format(lockSqlTemplate, idColumn,
				declaredAnnotation.name(), idColumn, timeout);
		log.trace("lockSql:{}", sql);

		Query nativeQuery = entityManager.createNativeQuery(sql);
		nativeQuery.setParameter(1, id);
		nativeQuery.setParameter(2, dataVersion);
		nativeQuery.getSingleResult();
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

}
