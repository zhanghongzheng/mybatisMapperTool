//package com.hydra.tool.zookeeper.curator.selector;
//
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.leader.LeaderSelector;
//import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
//
//import java.io.Closeable;
//import java.io.IOException;
//
///**
// * Created by lee on 15/11/30.
// */
//public class LeaderEngin extends LeaderSelectorListenerAdapter implements Closeable {
//    private final String name;
//    private final LeaderSelector leaderSelector;
//
//    public LeaderEngin(CuratorFramework client, String path, String name) {
//        this.name=name;
//        leaderSelector=new LeaderSelector(client, path, this);
//    }
//
//    public void start(){
//        leaderSelector.start();
//    }
//
//    @Override
//    public void close() throws IOException {
//        leaderSelector.close();
//    }
//
//    @Override
//    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
//
//    }
//}
