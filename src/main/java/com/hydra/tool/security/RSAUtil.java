//package com.hydra.tool.security;
//
//import com.hydra.tool.system.Sys;
//
//import javax.crypto.Cipher;
//import java.security.*;
//import java.security.spec.PKCS8EncodedKeySpec;
//import java.security.spec.X509EncodedKeySpec;
//
///**
// * Created by GongZheng on 15/10/26 下午10:28.
// * Describe:RSA加密
// */
//public class RSAUtil {
//    /**
//     * 获取钥匙对
//     *
//     * @return 钥匙对
//     * @throws NoSuchAlgorithmException
//     */
//    public static KeyPair getKeyPair() throws NoSuchAlgorithmException {
//        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//        keyPairGenerator.initialize(512);
//        return keyPairGenerator.generateKeyPair();
//    }
//
//    /**
//     * 获取公钥
//     *
//     * @param keyPair
//     * @return
//     */
//    public static String getPublicKey(KeyPair keyPair) {
//        return Base64.encode(keyPair.getPublic().getEncoded());
//    }
//
//    public static String getPrivateKey(KeyPair keyPair) {
//        return Base64.encode(keyPair.getPrivate().getEncoded());
//    }
//
//    public static PublicKey string2PublicKey(String pubStr) throws Exception {
//        byte[] bytes = Base64.decode(pubStr);
//        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(bytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePublic(keySpec);
//    }
//
//    public static PrivateKey string2PrivateKey(String priStr) throws Exception {
//        byte[] bytes = Base64.decode(priStr);
//        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
//        return keyFactory.generatePrivate(keySpec);
//    }
//
//    public static byte[] publicEncrypt(byte[] content, PublicKey publicKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
//        return cipher.doFinal(content);
//    }
//
//    public static byte[] privateDecrypt(byte[] content, PrivateKey privateKey) throws Exception {
//        Cipher cipher = Cipher.getInstance("RSA");
//        cipher.init(Cipher.DECRYPT_MODE, privateKey);
//        return cipher.doFinal(content);
//    }
//
//    public static void main(String[] args) throws Exception {
//        KeyPair keyPair = getKeyPair();
//        String data = Base64.encode(publicEncrypt("123".getBytes(), keyPair.getPublic()));
//        Sys.pl(data);
//        String source = new String(privateDecrypt(Base64.decode(data), keyPair.getPrivate()));
//        Sys.pl(source);
//    }
//}
