package com.godmonth.topia.basic.cache;

import java.util.Collection;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import com.google.common.cache.CacheStats;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class GuavaCacheExpirer {

	private CacheManager cacheManager;

	public GuavaCacheExpirer(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@SuppressWarnings("rawtypes")
	public void clean() {
		Collection<String> cacheNames = cacheManager.getCacheNames();
		for (String string : cacheNames) {
			Cache cache = cacheManager.getCache(string);
			if (cache != null) {
				com.google.common.cache.Cache nativeCache = (com.google.common.cache.Cache) cache.getNativeCache();
				nativeCache.cleanUp();
				CacheStats stats = nativeCache.stats();
				log.trace("cache name:{}, size:{}, {}", string, nativeCache.size(), stats);
			}
		}
	}

}
