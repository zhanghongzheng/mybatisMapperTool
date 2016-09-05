//package com.hydra.tool.zookeeper.curator;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.cache.NodeCache;
//import org.apache.curator.framework.recipes.cache.NodeCacheListener;
//import org.apache.zookeeper.data.Stat;
//
///**
// * Created by lee on 15/11/30.
// */
//public class NodeUtil {
//
//    public static String createNode() throws Exception {
//        CuratorFramework client = CuratorHelper.getInstance().getClient();
//        String path = client.create().creatingParentsIfNeeded().forPath(CuratorHelper.MASTER_PATH, "hello".getBytes());
//        return path;
//    }
//
//    public static void monitorNode(String path) throws Exception {
//        CuratorFramework client = CuratorHelper.getInstance().getClient();
//        final NodeCache cache = new NodeCache(client, path, false);
//        cache.start(true);
//        cache.getListenable().addListener(new NodeCacheListener() {
//            @Override
//            public void nodeChanged() throws Exception {
//                System.out.println("node change!" + new String(cache.getCurrentData().getData()));
//            }
//        });
//    }
//
//    public static void changeNode(String path) throws Exception {
//        CuratorFramework client = CuratorHelper.getInstance().getClient();
//        client.setData().forPath(path);
//    }
//
//
//}
