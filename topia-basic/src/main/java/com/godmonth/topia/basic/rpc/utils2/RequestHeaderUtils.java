package com.godmonth.topia.basic.rpc.utils2;

import com.godmonth.topia.rpc.request.Cacheable;
import com.godmonth.topia.rpc.request.RequestHeader;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.Validate;

/**
 * <p></p >
 *
 * @author shenyue
 */
public class RequestHeaderUtils {
    private RequestHeaderUtils() {
    }

    public static boolean isCacheable(RequestHeader header, Cacheable defaultCacheable) {
        Validate.isTrue(defaultCacheable == Cacheable.ENABLE || defaultCacheable == Cacheable.DISABLE, "defaultCacheable incorrect");
        Cacheable cacheable = ObjectUtils.defaultIfNull(header.getCacheable(), defaultCacheable);
        return cacheable == Cacheable.ENABLE;
    }


}
