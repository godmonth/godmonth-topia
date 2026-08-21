package com.godmonth.topia.data.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;

import org.apache.commons.lang3.ArrayUtils;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class MutableModel extends CreatedTimeModel {
	public static final String[] FIELD_LIST;
	static {
		String[] fieldList = { "lastUpdatedTime", "dataVersion" };
		FIELD_LIST = ArrayUtils.addAll(fieldList, CreatedTimeModel.FIELD_LIST);
	}

	@UpdateTimestamp
	@Column(name = "last_updated_time")
	private Date lastUpdatedTime;

	@Column(name = "data_version")
	@Version
	private Long dataVersion;

	public Date getLastUpdatedTime() {
		return lastUpdatedTime;
	}

	public void setLastUpdatedTime(Date lastUpdatedTime) {
		this.lastUpdatedTime = lastUpdatedTime;
	}

	public Long getDataVersion() {
		return dataVersion;
	}

	public void setDataVersion(Long dataVersion) {
		this.dataVersion = dataVersion;
	}

}
