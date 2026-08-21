package com.godmonth.topia.data.model;

public interface StatusEnabled<S> {
	S getStatus();

	void setStatus(S s);
}
