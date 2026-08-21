package com.godmonth.topia.concept.scope;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class IntScope {
	private Integer from;

	private Integer to;

	public Scope<Integer> toScope() {
		return new Scope<>(from, to);
	}

	public static IntScope fromScope(Scope<Integer> input) {
		return new IntScope(input.getFrom(), input.getTo());
	}

	public IntScope(Integer from, Integer to) {
		super();
		this.from = from;
		this.to = to;
	}

	public IntScope() {
		super();
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getTo() {
		return to;
	}

	public void setTo(Integer to) {
		this.to = to;
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("from", this.from).append("to", this.to)
				.toString();
	}

}
