package com.wojiushiwo.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 对密码进行MD5加密
 * Created by 我就是我
 * 2019/4/21 下午7:46
 */
public class MD5Util {

    private static final String salt = "1a2b3c4d";

    public static String md5(String str) {
        return DigestUtils.md5Hex(str);
    }

    //第一次MD5 用户端 PASS=MD5(明文+固定salt)
    public static String inputPassToFormPass(String inputPass) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    //第二次MD5 服务端 PASS=MD5(用户输入+随机salt)
    public static String formPassToDBPass(String formPass, String salt) {
        String str = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(str);
    }

    public static String inputPassToDbPass(String input, String saltDB) {
        String formPass = inputPassToFormPass(input);
        return formPassToDBPass(formPass, saltDB);
    }

    public static void main(String[] args) {
        System.out.println(inputPassToDbPass("123456",salt));
    }
}
