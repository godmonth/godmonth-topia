package com.godmonth.topia.rpc.cache;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * 根据id查询请求.返回对象根据参数决定是否缓存
 * 
 * @author shenyue
 *
 */
public class CachingId<T> implements Serializable {
	/**
	 * id
	 */
	@NotNull
	private T id;

	/**
	 * 是否缓存
	 */
	private boolean caching;

	public static <T> CachingId<T> cache(T id) {
		return new CachingId<T>(id, true);
	}

	public static <T> CachingId<T> nocache(T id) {
		return new CachingId<T>(id, false);
	}

	public CachingId() {
	}

	public CachingId(T id) {
		this.id = id;
	}

	public CachingId(T id, boolean caching) {
		this.id = id;
		this.caching = caching;
	}

	public boolean isCaching() {
		return caching;
	}

	public void setCaching(boolean caching) {
		this.caching = caching;
	}

	public T getId() {
		return id;
	}

	public void setId(T id) {
		this.id = id;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("id", this.id)
				.append("caching", this.caching).toString();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1665432593, -1073016025).append(this.id).append(this.caching).toHashCode();
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof CachingId)) {
			return false;
		}
		CachingId rhs = (CachingId) object;
		return new EqualsBuilder().append(this.id, rhs.id).append(this.caching, rhs.caching).isEquals();
	}

}
