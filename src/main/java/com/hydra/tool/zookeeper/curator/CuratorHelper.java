//package com.hydra.tool.zookeeper.curator;
//
//import com.hydra.tool.config.ConfigUtil;
//import com.hydra.tool.string.StrUtil;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.CuratorFrameworkFactory;
//import org.apache.curator.retry.RetryUntilElapsed;
//
//import java.util.Map;
//
///**
// * Created by lee on 15/11/30.
// */
//public class CuratorHelper {
//    private static final String CONNECT_STRING_NAME = "zookeeper.connectString";
//    private static final String SESSION_TIMEOUT_NAME = "zookeeper.sessionTimeout";
//    private static final String CONNECTION_TIMEOUT_NAME = "zookeeper.connectionTimeout";
//
//    public static final String MASTER_PATH = "/master";
//
//    private CuratorFramework client;
//
//    private CuratorHelper() {
//        Map<String, String> properties = ConfigUtil.getConfigsByFile("zookeeper", "zookeeper");
//        String connectString = properties.get(CONNECT_STRING_NAME);
//        String sessionTimeoutString = properties.get(SESSION_TIMEOUT_NAME);
//        String connectionTimeoutString = properties.get(CONNECTION_TIMEOUT_NAME);
//
//        if (StrUtil.isBlank(connectString)) {
//            throw new RuntimeException("zookeeper properties is error");
//        }
//        client = CuratorFrameworkFactory.builder()
//                .connectString(connectString)
//                .sessionTimeoutMs(Integer.parseInt(sessionTimeoutString))
//                .connectionTimeoutMs(Integer.parseInt(connectionTimeoutString))
//                .retryPolicy(new RetryUntilElapsed(5000, 1000))
//                .build();
//        client.start();
//    }
//
//    public CuratorFramework getClient() {
//        return client;
//    }
//
//    public static CuratorHelper getInstance() {
//        return SingletonHolder.INSANCE;
//    }
//
//    private static class SingletonHolder {
//        private final static CuratorHelper INSANCE = new CuratorHelper();
//
//    }
//
//}
