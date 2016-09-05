package com.hydra.tool.zookeeper.curator.service;

import com.hydra.tool.zookeeper.curator.ann.NodeMonitor;

import java.util.Map;

/**
 * Created by lee on 15/12/2.
 */
public class PersonManageImpl implements PersonManage {
    @Override
    @NodeMonitor(tag = "add")
    public void monitor(Map<String, Object> map) {
        System.out.println("do Service programm");
    }
}
