package com.wojiushiwo.redis;

/**
 * Created by 我就是我
 * 2019/4/21 下午2:29
 */
public class UserKey extends BasePrefix {
    public static UserKey getById = new UserKey("id");
    public static UserKey getByName = new UserKey("name");
    public UserKey(String prefix) {
        super(prefix);
    }

}
