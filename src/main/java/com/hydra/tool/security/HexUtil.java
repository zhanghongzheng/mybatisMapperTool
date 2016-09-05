package com.hydra.tool.security;

import com.hydra.tool.system.Sys;

/**
 * Created by GongZheng on 15/10/25 下午1:07.
 * Describe:
 */
public class HexUtil {
    public static String bytes2Hex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            byte b = bytes[i];
            boolean negative = false;
            if (b < 0) {
                negative = true;
            }
            int inte = b;
            if (negative) {
                inte = inte + 256;
            }
            String temp = Integer.toHexString(inte & 0xFF);
            if (temp.length() == 1) {
                sb.append("0");
            }
            sb.append(temp.toLowerCase());
        }
        return sb.toString();
    }

    public static byte[] hex2Bytes(String hex) {
        byte[] bytes = new byte[hex.length() / 2];
        for (int i=0; i<hex.length(); i = i + 2) {
            String subStr = hex.substring(i, i + 2);
            boolean negative = false;
            int inte = Integer.parseInt(subStr, 16);
            if (inte > 127) {
                negative = true;
            }
            if (inte == 128) {
                inte = -128;
            } else if (negative) {
                inte = inte - 256;
            }
            byte b = (byte) inte;
            bytes[i / 2] = b;
        }
        return bytes;
    }

    public static void main(String[] args) {
//        byte[] bytes = new byte[1];
//        bytes[0] = 127;
//        Sys.pl((int)(hex2Bytes(bytesToHexString(bytes)))[0]);
        Sys.pl(Integer.toHexString(-128));
//        byte i = 128;
        Sys.pl(127 | 0x80);
    }
}
