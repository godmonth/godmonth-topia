package com.godmonth.topia.idempotence;

import java.util.function.Function;
import java.util.function.Supplier;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

import com.godmonth.topia.data.model.IdObject;
import com.godmonth.topia.rpc.IdentifiableRequest;

public class IdempotenceTemplate<T, RI extends IdObject<Long>> {

	private RequestIdentityRepo<RI> requestIdentityRepo;

	private CrudRepository<T, Long> crudRepository;

	private TransactionTemplate transactionTemplate;

	private String parentType;

	private class RequestIdentitySupplier<REQ extends IdentifiableRequest> implements Supplier<RI> {
		private REQ request;

		public RequestIdentitySupplier(REQ request) {
			this.request = request;
		}

		@Override
		public RI get() {
			return requestIdentityRepo.save(request.getRequestIdentity(), parentType);
		}
	};

	private <REQ extends IdentifiableRequest> T getOrder(REQ request) {
		RI ri = requestIdentityRepo.findByRi(request.getRequestIdentity());
		if (ri != null) {
			return crudRepository.findById(ri.getId()).orElse(null);
		} else {
			return null;
		}
	}

	public <REQ extends IdentifiableRequest> T execute(final REQ request,
			final Function<IdempotenceParam<REQ, RI>, T> callback) {
		T order = getOrder(request);
		if (order == null) {
			order = transactionTemplate.execute(new TransactionCallback<T>() {

				@Override
				public T doInTransaction(TransactionStatus arg0) {
					return callback
							.apply(new IdempotenceParam<REQ, RI>(new RequestIdentitySupplier<REQ>(request), request));
				}
			});
		}
		return order;
	}

	public void setRequestIdentityRepo(RequestIdentityRepo<RI> requestIdentityRepo) {
		this.requestIdentityRepo = requestIdentityRepo;
	}

	public void setCrudRepository(CrudRepository<T, Long> crudRepository) {
		this.crudRepository = crudRepository;
	}

	public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
		this.transactionTemplate = transactionTemplate;
	}

	public void setParentType(String parentType) {
		this.parentType = parentType;
	}

}
