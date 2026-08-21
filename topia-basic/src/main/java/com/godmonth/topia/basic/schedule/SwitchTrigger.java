package com.godmonth.topia.basic.schedule;

import java.time.Instant;

import org.springframework.lang.Nullable;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;

public class SwitchTrigger implements Trigger {

	private Trigger trigger;

	private boolean enabled;

	@Override
	@Nullable
	public Instant nextExecution(TriggerContext context) {
		if (enabled) {
			return trigger.nextExecution(context);
		}
		return null;
	}

	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

}
