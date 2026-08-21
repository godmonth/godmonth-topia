package com.godmonth.topia.data.utils;

import org.apache.commons.lang3.Validate;

import com.godmonth.topia.data.model.IdObject;

public abstract class RankMovement<DOMAIN extends com.godmonth.topia.data.model.RankEnabled & IdObject<Long>, PID> {

	public void move(final DOMAIN domain, final int rank) {
		if (domain.getRank() == rank) {
			return;
		}
		final int modifier = domain.getRank() > rank ? 1 : -1;
		recursiveMove(domain, rank, domain.getId(), modifier);
	}

	private static final int TMP_RANK = -1;

	private void recursiveMove(DOMAIN domain, int targetRank, Long sourceId, int modifier) {
		DOMAIN placeHolder = findByRank(getParentId(domain), targetRank);
		if (placeHolder != null) {
			if (sourceId == placeHolder.getId() && sourceId != domain.getId()) {
				int updateRank = updateRank(placeHolder.getId(), placeHolder.getRank(), TMP_RANK);
				Validate.isTrue(updateRank == 1);
			} else {
				recursiveMove(placeHolder, placeHolder.getRank() + modifier, sourceId, modifier);
			}
		}
		int sourceRank = domain.getId() == sourceId ? TMP_RANK : domain.getRank();
		int updateRank = updateRank(domain.getId(), sourceRank, targetRank);
		Validate.isTrue(updateRank == 1);

	}

	protected abstract PID getParentId(DOMAIN domain);

	protected abstract DOMAIN findByRank(PID parentId, int rank);

	protected abstract int updateRank(Long id, int sourceRank, int targetRank);

}
