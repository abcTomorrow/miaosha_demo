package com.wojiushiwo.redis;

import lombok.AllArgsConstructor;

/**
 * Created by 我就是我
 * 2019/4/21 下午2:28
 */
@AllArgsConstructor
public abstract class BasePrefix implements KeyPrefix {

    private int expireSeconds;

    private String prefix;

    public BasePrefix(String prefix) {
        this(0, prefix);
    }

    @Override
    public int expireSeconds() {
        //默认0 表示永不过期
        return 0;
    }

    @Override
    public String getPrefix() {
        String className = getClass().getSimpleName();
        return className + ":" + prefix;
    }
}
