package com.godmonth.topia.data.lock;

import java.io.Serializable;

import com.godmonth.topia.data.model.MutableModel;

public interface LockOrder<ID extends Serializable> {
	void lock(Class<? extends MutableModel> entityClass, ID id, long dataVersion);

}
