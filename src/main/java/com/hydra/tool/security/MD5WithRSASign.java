//package com.hydra.tool.security;
//
//import com.hydra.tool.system.Sys;
//
//import java.security.*;
//
///**
// * Created by GongZheng on 15/10/27 下午10:45.
// * Describe: MD5和RSA配合验签
// */
//public class MD5WithRSASign {
//    /**
//     * @param content    待摘要内容
//     * @param privateKey 私钥
//     * @return 摘要
//     * @throws Exception
//     */
//    public static byte[] sign(byte[] content, PrivateKey privateKey) throws Exception {
//        Signature signature = Signature.getInstance("MD5withRSA");
//        signature.initSign(privateKey);
//        signature.update(content);
//        return signature.sign();
//    }
//
//    /**
//     * @param content   传输内容
//     * @param sign      摘要
//     * @param publicKey 公钥
//     * @return boolean
//     * @throws Exception
//     */
//    public static boolean verify(byte[] content, byte[] sign, PublicKey publicKey) throws Exception {
//        Signature signature = Signature.getInstance("MD5withRSA");
//        signature.initVerify(publicKey);
//        signature.update(content);
//        return signature.verify(sign);
//    }
//
//    public static void main(String[] args) throws Exception {
//        KeyPair keyPair = RSAUtil.getKeyPair();
//        byte[] bytes = sign("123".getBytes(), keyPair.getPrivate());
//        Sys.pl(verify("1234".getBytes(), bytes, keyPair.getPublic()));
//    }
//}
