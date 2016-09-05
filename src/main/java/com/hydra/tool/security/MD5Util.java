package com.hydra.tool.security;

import java.security.MessageDigest;

/**
 * Created by ZhengGong on 15/3/23.
 * Description md5加密算法工具
 */
public class MD5Util {
    public static String md5To16WithHex(String plainText) throws Exception {
        return md5To32(plainText).substring(8, 24);
    }

    public static String md5To32(String plainText) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        return HexUtil.bytes2Hex(md.digest(plainText.getBytes("utf-8")));
    }

    public static void main(String[] args) throws Exception {
        System.out.println(MD5Util.md5To32("123456"));
    }
}