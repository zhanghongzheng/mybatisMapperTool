//package com.hydra.tool.zookeeper.curator.listener;
//
//import com.google.common.collect.Lists;
//import com.google.common.collect.Maps;
//import com.hydra.tool.clazz.ClassUtil;
//import com.hydra.tool.object.ObjectUtil;
//import com.hydra.tool.zookeeper.curator.ann.Listener;
//import com.hydra.tool.zookeeper.curator.ann.NodeListener;
//import org.springframework.beans.factory.BeanFactory;
//
//import java.lang.reflect.Method;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
///**
// * Created by lee on 15/12/2.
// */
//public class ListenerContext {
//    private final Map<String, ListenerEntity> enginMap = Maps.newConcurrentMap();
//    private final List<ListenerEntity> enginList = Lists.newCopyOnWriteArrayList();
//
//    private BeanFactory beanFactory;
//    private String pkgName;
//    private ListenerEntity entity;
//
//    public ListenerContext(String pkgName, BeanFactory beanFactory) {
//        this.pkgName = pkgName;
//        this.beanFactory = beanFactory;
//    }
//
//    public Map<String, ListenerEntity> getEnginMap() throws Exception {
//        return enginMap;
//    }
//
//    public void init() {
//        enginList.addAll(load());
//        for (ListenerEntity entity : enginList) {
//            enginMap.put("map", entity);
//        }
//    }
//
//    public List<ListenerEntity> load() {
//        List<ListenerEntity> entities = Lists.newArrayList();
//        Set<Class<?>> classSet = ClassUtil.scanPackageByAnnotation(pkgName, Listener.class);
//        for (Class<?> clazz : classSet) {
//            Object obj = beanFactory.getBean(clazz);
//            if (ObjectUtil.isNotNull(obj)) {
//                Method[] methods = clazz.getDeclaredMethods();
//                for (Method method : methods) {
//                    NodeListener listener = method.getAnnotation(NodeListener.class);
//                    if (ObjectUtil.isNotNull(listener)) {
//                        checkMethod(method);
//                        entities.add(new ListenerEntity(obj, method, listener.value(), listener.desc(), listener.systemRefer()));
//                    }
//                }
//            }
//        }
//        return entities;
//    }
//
//    private void checkMethod(Method method) {
//
//    }
//}
