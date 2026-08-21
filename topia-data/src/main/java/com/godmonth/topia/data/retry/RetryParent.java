package com.godmonth.topia.data.retry;

public class RetryParent<T> {
	private T parent;

	private String parentType;

	private Long parentId;

	private String command;

	public RetryParent() {
	}

	public RetryParent(T parent, String parentType, Long parentId, String command) {
		this.parent = parent;
		this.parentType = parentType;
		this.parentId = parentId;
		this.command = command;
	}

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public String getParentType() {
		return parentType;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
