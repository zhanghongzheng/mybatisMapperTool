package com.hydra.tool.zookeeper.curator.service;

import com.hydra.tool.zookeeper.curator.ann.Listener;
import com.hydra.tool.zookeeper.curator.ann.NodeListener;

import java.util.Map;

/**
 * Created by lee on 15/12/1.
 */
@Listener
public class UserManageImpl implements UserManage{
    @Override
    @NodeListener(value = "listener",desc = "")
    public void listener() {
        System.out.println("doListener");
    }
}
