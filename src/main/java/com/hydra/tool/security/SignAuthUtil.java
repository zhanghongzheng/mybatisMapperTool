//package com.hydra.tool.security;
//
//
//import com.hydra.tool.exception.ParamsAuthException;
//
//import java.net.URLDecoder;
//import java.net.URLEncoder;
//import java.util.UUID;
//import java.util.concurrent.LinkedBlockingQueue;
//
///**
// * Created by GongZheng on 15/6/3 下午11:48.
// * Describe: 权限加密工具
// */
//public class SignAuthUtil {
//    private static final LinkedBlockingQueue<String> blockingQueue = new LinkedBlockingQueue<String>();
//    private static final int COUNT = 20000;
//
//    public static String getValue(String auth, String tk) throws ParamsAuthException {
//        try {
//            if (blockingQueue.contains(auth)) {
//                throw new ParamsAuthException();
//            } else {
//                blockingQueue.put(auth);
//                if (blockingQueue.size() > COUNT) {
//                    blockingQueue.poll();
//                }
//            }
//
//            String str = DesUtil.decryptDESWithBase64(URLDecoder.decode(tk, "utf-8"), auth.substring(0, 8));
//            String md = str.substring(0, 32);
//            String value = str.substring(33, str.length());
//            if (md.equals(auth)) {
//                return value;
//            }
//        } catch (Exception ignored) {
//            ignored.printStackTrace();
//        }
//        throw new ParamsAuthException();
//    }
//
//    public static String buildValue(String auth, String value) throws Exception {
//        return URLEncoder.encode(DesUtil.encryptDESWithBase64(auth + "@" + value, auth.substring(0, 8)), "utf-8");
//    }
//
//    public static String buildAuth() throws Exception {
//        return MD5Util.md5To32(UUID.randomUUID().toString());
//    }
//
//    public static void main(String[] args) throws Exception {
//        String auth = buildAuth();
//
//        System.out.println(auth);
//
//        for (int i=0; i<10; i++) {
//
//            System.out.println((buildValue(auth, "哈哈")));
//
//            System.out.println(getValue(auth, buildValue(auth, "?%%不会吧")));
//            System.out.println(blockingQueue);
//        }
//    }
//}
