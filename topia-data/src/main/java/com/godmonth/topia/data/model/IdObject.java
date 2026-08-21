package com.godmonth.topia.data.model;

import java.io.Serializable;

public interface IdObject<ID extends Serializable> {
	/**
	 * Returns the id of the entity.
	 * 
	 * @return the id
	 */
	ID getId();
}
