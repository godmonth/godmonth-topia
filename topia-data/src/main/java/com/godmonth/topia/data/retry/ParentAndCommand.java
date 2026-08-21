package com.godmonth.topia.data.retry;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class ParentAndCommand<T, CMD extends Statusable> {
	private T parent;
	private CMD command;

	public ParentAndCommand(T parent, CMD command) {
		this.parent = parent;
		this.command = command;
	}

	public T getParent() {
		return parent;
	}

	public CMD getCommand() {
		return command;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("parent", this.parent)
				.append("command", this.command).toString();
	}

}
