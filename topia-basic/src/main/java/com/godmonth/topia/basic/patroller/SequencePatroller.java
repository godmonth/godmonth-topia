package com.godmonth.topia.basic.patroller;

import java.util.List;

import org.apache.commons.lang3.Validate;

import com.godmonth.topia.basic.competition.LockTemplate2;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SequencePatroller {

	public static final String DEFAULT_KEY = "PATROL";

	private LockTemplate2 lockTemplate;

	private List<Patroller> patrollers;

	private String lockKey = DEFAULT_KEY;

	public void patrol() {
		log.info("patrol begin:{}", lockKey);
		lockTemplate.execute(lockKey, () -> {
			for (Patroller patroller : patrollers) {
				try {
					patroller.patrol();
				} catch (Exception e) {
					log.error("", e);
				}
			}
			return null;
		});
		log.info("patrol end:{}", lockKey);
	}

	public void setPatrollers(List<Patroller> patrollers) {
		this.patrollers = patrollers;
	}

	public void setLockTemplate(LockTemplate2 lockTemplate) {
		this.lockTemplate = lockTemplate;
	}

	public void setLockKey(String lockKey) {
		Validate.notBlank(lockKey, "lockKey is blank");
		this.lockKey = lockKey;
	}
}
