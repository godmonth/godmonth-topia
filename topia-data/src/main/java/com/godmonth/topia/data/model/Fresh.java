package com.godmonth.topia.data.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Fresh<T> {
	private T entity;

	private boolean fresh;

	public Fresh(T entity, boolean fresh) {
		this.entity = entity;
		this.fresh = fresh;
	}

	public T getEntity() {
		return entity;
	}

	public boolean isFresh() {
		return fresh;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("entity", this.entity).append("fresh", this.fresh).toString();
	}

}
