//package com.hydra.tool.security;
//
//import com.hydra.tool.system.Sys;
//
//import javax.crypto.Cipher;
//import javax.crypto.KeyGenerator;
//import javax.crypto.SecretKey;
//import javax.crypto.spec.IvParameterSpec;
//import javax.crypto.spec.SecretKeySpec;
//import java.io.IOException;
//import java.security.NoSuchAlgorithmException;
//
///**
// * Created by GongZheng on 15/6/1 下午11:41.
// * Describe:
// */
//public class DesUtil {
//    private static byte[] iv = {1, 2, 3, 4, 5, 6, 7, 8};
//
//    /**
//     * 加密字符串 通过Base64
//     *
//     * @param encryptString 待加密字符串
//     * @param encryptKey    key
//     * @return Base64后的加密串
//     * @throws Exception
//     */
//    public static String encryptDESWithBase64(String encryptString, String encryptKey) throws Exception {
//        IvParameterSpec zeroIv = new IvParameterSpec(iv);
//        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
//        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, key, zeroIv);
//        byte[] encryptedData = cipher.doFinal(encryptString.getBytes());
//        return Base64.encode(encryptedData);
//    }
//
//    /**
//     * 解密字符串 通过Base64
//     *
//     * @param decryptString 待解密字符串
//     * @param decryptKey    key
//     * @return Base64后的解密串
//     * @throws Exception
//     */
//    public static String decryptDESWithBase64(String decryptString, String decryptKey) throws Exception {
//        byte[] byteMi = Base64.decode(decryptString);
//        IvParameterSpec zeroIv = new IvParameterSpec(iv);
//        SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
//        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
//        cipher.init(Cipher.DECRYPT_MODE, key, zeroIv);
//        byte decryptedData[] = cipher.doFinal(byteMi);
//        return new String(decryptedData);
//    }
//
//    public static void main(String[] args) throws Exception {
//        String key = genKeyDES();
//        String source = Base64.encode(encryptDES("123@123".getBytes(), loadKeyDES(key)));
//        String data = new String(decryptDES(Base64.decode(source), loadKeyDES(key)));
//        Sys.pl(data);
//
//        System.out.println(decryptDESWithBase64(encryptDESWithBase64("123@123", "99999999"), "99999999"));
//    }
//
//    public static String genKeyDES() throws NoSuchAlgorithmException {
//        KeyGenerator keyGenerator = KeyGenerator.getInstance("DES");
//        keyGenerator.init(56);
//        SecretKey key = keyGenerator.generateKey();
//        return Base64.encode(key.getEncoded());
//    }
//
//    public static SecretKey loadKeyDES(String base64Key) throws IOException {
//        byte[] bytes = Base64.decode(base64Key);
//        return new SecretKeySpec(bytes, "DES");
//    }
//
//
//    /**
//     * 加密des
//     *
//     * @param source 原byte
//     * @param key    密钥
//     * @return byte[]
//     * @throws Exception
//     */
//    public static byte[] encryptDES(byte[] source, SecretKey key) throws Exception {
//        Cipher cipher = Cipher.getInstance("DES");
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
//        Cipher cipher = Cipher.getInstance("DES");
//        cipher.init(Cipher.DECRYPT_MODE, key);
//        return cipher.doFinal(source);
//    }
//}
