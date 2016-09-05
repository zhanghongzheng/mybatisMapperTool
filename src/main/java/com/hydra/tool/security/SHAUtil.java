package com.hydra.tool.security;

import com.hydra.tool.system.Sys;

import java.security.MessageDigest;

/**
 * Created by GongZheng on 15/10/25 下午3:20.
 * Describe: sha-1加密
 */
public class SHAUtil {
    public static String sha1(String str) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        return HexUtil.bytes2Hex(md.digest(str.getBytes("utf-8")));
    }

    public static void main(String[] args) throws Exception {
        Sys.pl(sha1("12355555555555"));
        Sys.pl(MD5Util.md5To32("12355555555555"));

    }
}
