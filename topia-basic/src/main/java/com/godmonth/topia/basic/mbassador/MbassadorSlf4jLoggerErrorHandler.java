package com.godmonth.topia.basic.mbassador;

import net.engio.mbassy.bus.error.IPublicationErrorHandler;
import net.engio.mbassy.bus.error.PublicationError;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MbassadorSlf4jLoggerErrorHandler implements IPublicationErrorHandler {

	@Override
	public void handleError(PublicationError error) {
		log.error("", error.getCause());
	}

}
