package com.godmonth.topia.idempotence;

import java.util.function.Supplier;

import com.godmonth.topia.data.model.IdObject;

public class IdempotenceParam<REQ, RI extends IdObject<Long>> {
	private Supplier<RI> requestIdentitySupplier;
	private REQ request;

	public IdempotenceParam(Supplier<RI> requestIdentitySupplier, REQ request) {
		this.requestIdentitySupplier = requestIdentitySupplier;
		this.request = request;
	}

	public Supplier<RI> getRequestIdentitySupplier() {
		return requestIdentitySupplier;
	}

	public REQ getRequest() {
		return request;
	}

}
