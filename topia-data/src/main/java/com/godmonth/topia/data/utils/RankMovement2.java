package com.godmonth.topia.data.utils;

import java.io.Serializable;

import org.springframework.transaction.TransactionStatus;

import com.godmonth.topia.data.model.IdObject;

public abstract class RankMovement2<DOMAIN extends com.godmonth.topia.data.model.RankEnabled & IdObject<ID>, ID extends Serializable, PID> {

	public void move(DOMAIN domain, final int rank, TransactionStatus status) {
		if (domain.getRank() == rank) {
			return;
		}
		PID parentId = getParentId(domain);
		DOMAIN target = findByRank(parentId, rank);
		if (target != null) {
			int modifier = domain.getRank() > rank ? 1 : -1;
			updateRank(domain.getId(), TMP_RANK);
			status.flush();
			recursiveMove(target, modifier);
			status.flush();
		}
		updateRank(domain.getId(), rank);

	}

	private static final int TMP_RANK = -1;

	private void recursiveMove(DOMAIN domain, int modifier) {
		int targetRank = domain.getRank() + modifier;
		DOMAIN placeHolder = findByRank(getParentId(domain), targetRank);
		if (placeHolder != null) {
			recursiveMove(placeHolder, modifier);
		}
		updateRank(domain.getId(), targetRank);
	}

	protected abstract PID getParentId(DOMAIN domain);

	protected abstract DOMAIN findByRank(PID parentId, int rank);

	protected abstract void updateRank(ID id, int targetRank);

}
