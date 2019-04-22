package com.wojiushiwo.redis;

/**
 * Created by 我就是我
 * 2019/4/21 下午2:30
 */
public class OrderKey extends BasePrefix {
    public OrderKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
}
