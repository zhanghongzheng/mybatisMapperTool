//package com.hydra.tool.zookeeper.zkclient;
//
//import com.github.zkclient.ZkClient;
//import com.hydra.tool.config.ConfigUtil;
//import com.hydra.tool.string.StrUtil;
//import org.apache.zookeeper.KeeperException;
//
//import java.io.IOException;
//import java.util.Map;
//
///**
// * Created by ZhengGong on 15/6/3.
// * Description
// */
//public class ZookeeperHelper {
//    // 会话超时时间，设置为与系统默认时间一致
//    private static final String CONNECT_STRING_NAME = "zookeeper.connectString";
//    private static final String SESSION_TIMEOUT_NAME = "zookeeper.sessionTimeout";
//    private static final String CONNECTION_TIMEOUT_NAME = "zookeeper.connectionTimeout";
//    private static ZookeeperHelper zookeeperHelper = new ZookeeperHelper();
//
//    private static volatile boolean isInit = false;
//
//    private ZkClient zkClient;
//
//    public static ZookeeperHelper getInstance() {
//        if (!isInit) {
//            synchronized (ZookeeperHelper.class) {
//                if (!isInit) {
//                    Map<String, String> properties = ConfigUtil.getConfigsByFile("zookeeper", "zookeeper");
//                    String connectString = properties.get(CONNECT_STRING_NAME);
//                    String sessionTimeoutString = properties.get(SESSION_TIMEOUT_NAME);
//                    String connectionTimeoutString = properties.get(CONNECTION_TIMEOUT_NAME);
//
//                    if (StrUtil.isBlank(connectString)) {
//                        throw new RuntimeException("zookeeper properties is error");
//                    }
//                    if (!StrUtil.hasBlank(sessionTimeoutString, connectionTimeoutString)) {
//                        zookeeperHelper.zkClient = new ZkClient(connectString,
//                                Integer.valueOf(sessionTimeoutString),
//                                Integer.valueOf(connectionTimeoutString));
//                    } else if (StrUtil.isNotBlank(connectionTimeoutString)) {
//                        zookeeperHelper.zkClient = new ZkClient(connectString,
//                                Integer.valueOf(connectionTimeoutString));
//                    } else {
//                        zookeeperHelper.zkClient = new ZkClient(connectString);
//                    }
//
//                    isInit = true;
//                }
//            }
//        }
//
//        return zookeeperHelper;
//    }
//
//    public void close() {
//
//    }
//
//    public ZkClient getZkClient() {
//        return zkClient;
//    }
//
//    public static void main(String[] args) throws IOException, InterruptedException, KeeperException {
//
//    }
//}
