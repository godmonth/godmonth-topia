package com.godmonth.topia.concept.property;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 物权索引
 * 
 * @author godmonth
 *
 * @param <OWNER>
 * @param <OBJ>
 */
public class PropertyIndex<OWNER, OBJ> {
	/**
	 * 物主id
	 */
	@NotNull
	@Valid
	private OWNER ownerId;
	/**
	 * 物品id
	 */
	@NotNull
	@Valid
	private OBJ objectId;

	public PropertyIndex() {
		super();
	}

	public PropertyIndex(@NotNull @Valid OWNER ownerId, @NotNull @Valid OBJ objectId) {
		this.ownerId = ownerId;
		this.objectId = objectId;
	}

	public OWNER getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(OWNER ownerId) {
		this.ownerId = ownerId;
	}

	public OBJ getObjectId() {
		return objectId;
	}

	public void setObjectId(OBJ objectId) {
		this.objectId = objectId;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof PropertyIndex)) {
			return false;
		}
		PropertyIndex rhs = (PropertyIndex) object;
		return new EqualsBuilder().append(this.ownerId, rhs.ownerId).append(this.objectId, rhs.objectId).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-1551464235, -1322009919).append(this.ownerId).append(this.objectId).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("ownerId", this.ownerId).append("objectId", this.objectId).toString();
	}

}
