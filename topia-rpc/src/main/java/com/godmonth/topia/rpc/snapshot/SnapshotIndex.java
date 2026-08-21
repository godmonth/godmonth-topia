package com.godmonth.topia.rpc.snapshot;

import java.io.Serializable;
import java.util.Date;

import jakarta.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 查询索引在某个时间点上的快照值
 * 
 * @author shenyue
 *
 */
public class SnapshotIndex<T> implements Serializable {
	/**
	 * index
	 */
	@NotNull
	private T index;

	/**
	 * 快照时间
	 */
	@NotNull
	private Date snapshotTime;

	public static <T> SnapshotIndex<T> snapshot(T index, Date snapshotTime) {
		return new SnapshotIndex<T>(index, snapshotTime);
	}

	public SnapshotIndex() {
	}

	public SnapshotIndex(@NotNull T index, @NotNull Date snapshotTime) {
		this.index = index;
		this.snapshotTime = snapshotTime;
	}

	public T getIndex() {
		return index;
	}

	public void setIndex(T index) {
		this.index = index;
	}

	public Date getSnapshotTime() {
		return snapshotTime;
	}

	public void setSnapshotTime(Date snapshotTime) {
		this.snapshotTime = snapshotTime;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("index", this.index).append("snapshotTime", this.snapshotTime)
				.toString();
	}

}
