package com.godmonth.topia.idempotence;

import com.godmonth.topia.data.model.IdObject;
import com.godmonth.topia.rpc.RequestIdentity;

public interface RequestIdentityRepo<RI extends IdObject<Long>> {
	RI findByRi(RequestIdentity requestIdentity);

	RI save(RequestIdentity requestIdentity, String parentType);
}
