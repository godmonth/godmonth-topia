package com.godmonth.topia.rpc.request;

/**
 * <p>缓存开关.默认是AUTO</p >
 *
 * @author shenyue
 */
public enum Cacheable {
    /**
     * 要求开启缓存
     */
    ENABLE,
    /**
     * 要求关闭
     */
    DISABLE,
    /**
     * 自动.有服务端决定
     */
    AUTO
}
