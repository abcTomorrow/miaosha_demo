package com.wojiushiwo.redis;

/**
 * Created by 我就是我
 * 2019/4/21 下午2:27
 */
public interface KeyPrefix {

    int expireSeconds();

    String getPrefix();

}
