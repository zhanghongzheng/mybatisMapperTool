//package com.hydra.tool.security;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//
///**
// * Created by GongZheng on 15/10/25 下午4:21.
// * Describe:
// */
//public class AESUtil {
//    public static String genKeyAES() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//        keyGenerator.init(128);
//        SecretKey key = keyGenerator.generateKey();
//        return Base64.encode(key.getEncoded());
//    }
//
//    public static SecretKey loadKeyAES(String base64Key) throws IOException {
//        byte[] bytes = Base64.decode(base64Key);
//        return new SecretKeySpec(bytes, "AES");
//    }
//
//    /**
//     * 加密des
//     *
//     * @param source 原byte
//     * @param key    密钥
//     * @return byte[]
//     * @throws Exception
//     */
//    public static byte[] encryptAES(byte[] source, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        return cipher.doFinal(source);
//    }
//
//    /**
//     * 解密
//     *
//     * @param source 带解密数据
//     * @param key    密钥
//     * @return byte[]
//     * @throws Exception
//     */
//    public static byte[] decryptDES(byte[] source, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES");
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        return cipher.doFinal(source);
//    }
//}
