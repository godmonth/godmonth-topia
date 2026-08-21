package com.godmonth.topia.basic.config;

import java.util.List;

public interface ConfigReader<KEY, CONF> {
	CONF getConfig(KEY key);

	List<CONF> getConfigList();
	
}
