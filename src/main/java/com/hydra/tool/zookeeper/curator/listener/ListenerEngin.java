//package com.hydra.tool.zookeeper.curator.listener;
//
//import com.hydra.tool.config.ConfigUtil;
//import com.hydra.tool.object.ObjectUtil;
//import com.hydra.tool.zookeeper.curator.CuratorHelper;
//import com.hydra.tool.zookeeper.curator.NodeUtil;
//import org.apache.curator.framework.CuratorFramework;
//import org.apache.curator.framework.recipes.cache.NodeCache;
//import org.apache.curator.framework.recipes.cache.NodeCacheListener;
//import org.springframework.beans.factory.BeanFactory;
//
//import javax.annotation.PostConstruct;
//import javax.annotation.Resource;
//import java.util.Map;
//
///**
// * Created by lee on 15/12/1.
// */
//public class ListenerEngin {
//    private volatile ListenerContext context;
//    private Map<String, String> listenerMap;
//
//    @Resource
//    private BeanFactory beanFactory;
//
//    public void invoke() throws Exception {
//        ListenerEntity entity = context.getEnginMap().get("map");
//        entity.method.invoke(entity.target);
//    }
//
//    @PostConstruct
//    public void init() throws Exception {
//        listenerMap = ConfigUtil.getConfigsByFile("zookeeper", "zookeeper");
//        ListenerContext enginContext = new ListenerContext(listenerMap.get("scan.pkg.name"), beanFactory);
//        enginContext.load();
//        enginContext.init();
//        context = enginContext;
//        monitorNode("/master");
//    }
//
//    public void monitorNode(String path) throws Exception {
//        CuratorFramework client = CuratorHelper.getInstance().getClient();
//        final NodeCache cache = new NodeCache(client, path, false);
//        cache.start(true);
//        cache.getListenable().addListener(new NodeCacheListener() {
//            @Override
//            public void nodeChanged() throws Exception {
//                invoke();
//            }
//        });
//    }
//}
