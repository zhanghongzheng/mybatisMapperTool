package com.hydra.tool.zookeeper.curator.listener;

import java.lang.reflect.Method;

/**
 * Created by lee on 15/12/1.
 */
public class ListenerEntity {
    public final Object target;
    public final Method method;
    public final String name;
    public final String desc;
    public final String systemRefer;

    public ListenerEntity(Object target, Method method, String name, String desc, String systemRefer) {
        this.target = target;
        this.method = method;
        this.name = name;
        this.desc = desc;
        this.systemRefer = systemRefer;
    }
}
