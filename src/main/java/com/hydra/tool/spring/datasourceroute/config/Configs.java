//package com.hydra.tool.spring.datasourceroute.config;
//
//import com.google.common.collect.Maps;
//import org.apache.commons.dbcp.BasicDataSource;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;
//import org.springframework.beans.factory.support.BeanDefinitionBuilder;
//import org.springframework.context.support.GenericApplicationContext;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.Properties;
//
///**
// * Created by ZhengGong on 15/6/16.
// * Description
// */
//public final class Configs {
//    private static Map<Object, Object> dataSourceMap = Maps.newConcurrentMap();
//    private static Map<String, Properties> propMap = Maps.newConcurrentMap();
//    private static final String defaultPathKey = "dataSourcePath";
//    private static final String defaultPathValue = "datasources";
//    private static final String defaultDataSourceKey = "defaultDataSource";
//    private static final String defaultDataSourceValue = "defaultDataSource";
//    private static final String bakDataSourceKey = "bakDataSource";
//    private static final String bakDataSourceValue = "bakDataSource";
//    private static final String defaultHeartbeatKey = "heartbeat";
//    private static final String defaultHeartbeatValue = "flase";
//    private static final String heartbeatTimeKey = "heartbeatTime";
//    private static final String heartbeatTimeValue = "10000";
//    private static final String heartbeatDelayKey = "heartbeatDelay";
//    private static final String heartbeatDelayValue = "300000";
//    private static final String defaultHeartbeatSQLKey = "heartbeatSQL";
//    private static final String defaultHeartbeatSQLValue = "select 1";
//    private static final String defaultConfigPath = "datasources.properties";
//    private static String defaultDataSource = "defaultDataSource";
//    private static String bakDataSource = "";
//    private static String heartbeat = "";
//    private static String heartbeatSQL = "";
//    private static String heartbeatTime = "";
//    private static String heartbeatDelay = "";
//    private static Log LOG = LogFactory.getLog(Configs.class);
//
//    public Configs() {
//    }
//
//    private static void loadProp(String folderPath, boolean isClear) {
//        try {
//            if (isClear) {
//                propMap.clear();
//            }
//
//            String e = Configs.class.getClassLoader().getResource(folderPath).getPath();
//            File folder = new File(e);
//            File[] files = folder.listFiles();
//            int len$ = files.length;
//
//            for (int i$ = 0; i$ < len$; ++i$) {
//                File file = files[i$];
//                Properties properties = new Properties();
//                properties.load(new FileInputStream(file));
//                propMap.put(file.getName().substring(0, file.getName().lastIndexOf(46)), properties);
//            }
//        } catch (Exception var10) {
//            LOG.error("load file error!", var10);
//        }
//
//    }
//
//    public static void init() {
//        Properties config = new Properties();
//
//        try {
//            config.load(Configs.class.getClassLoader().getResourceAsStream("datasources.properties"));
//            String e = config.getProperty("dataSourcePath", "datasources");
//            defaultDataSource = config.getProperty("defaultDataSource", "defaultDataSource");
//            bakDataSource = config.getProperty("bakDataSource", "bakDataSource");
//            heartbeat = config.getProperty("heartbeat", "flase");
//            heartbeatSQL = config.getProperty("heartbeatSQL", "select 1");
//            heartbeatTime = config.getProperty("heartbeatTime", "10000");
//            heartbeatDelay = config.getProperty("heartbeatDelay", "300000");
//            initDataSource(e);
//        } catch (IOException var2) {
//            LOG.error("load config file error!", var2);
//        }
//
//    }
//
//    private static void initDataSource(String dataSourcePath) {
//        loadProp(dataSourcePath, true);
//        GenericApplicationContext ctx = new GenericApplicationContext();
//        addPropBean(ctx);
//        StringBuilder sb = new StringBuilder();
//        String[] arr$ = ctx.getBeanDefinitionNames();
//        int len$ = arr$.length;
//
//        for (int i$ = 0; i$ < len$; ++i$) {
//            String name = arr$[i$];
//            sb.append(name + ",");
//        }
//
//        LOG.debug("Configs init:" + sb.toString());
//    }
//
//    private static void addPropBean(GenericApplicationContext ctx) {
//        Iterator i$ = propMap.keySet().iterator();
//
//        while (i$.hasNext()) {
//            String key = (String) i$.next();
//            Properties prop = (Properties) propMap.get(key);
//            BeanDefinitionBuilder beanDefBuilder = BeanDefinitionBuilder.rootBeanDefinition(BasicDataSource.class);
//            Iterator i$1 = prop.keySet().iterator();
//
//            while (i$1.hasNext()) {
//                Object propKey = i$1.next();
//                beanDefBuilder.addPropertyValue(propKey.toString(), prop.getProperty(propKey.toString()));
//            }
//
//            ctx.registerBeanDefinition(key, beanDefBuilder.getBeanDefinition());
//            dataSourceMap.put(key, ctx.getBean(key));
//        }
//
//    }
//
//    public static Map<Object, Object> getDatasources() {
//        return dataSourceMap;
//    }
//
//    public static Object getDefaultDataSource() {
//        return dataSourceMap.get(defaultDataSource);
//    }
//
//    public static Object getBakDataSource() {
//        return dataSourceMap.get(bakDataSource);
//    }
//
//    public static String getHeartbeatSQL() {
//        return heartbeatSQL;
//    }
//
//    public static boolean isHeartbeat() {
//        return Boolean.valueOf(heartbeat.toLowerCase());
//    }
//
//    public static long getHeartbeatTime() {
//        return Long.valueOf(heartbeatTime);
//    }
//
//    public static long getHeartbeatDelay() {
//        return Long.valueOf(heartbeatDelay);
//    }
//
//    public static boolean containsKey(String datasourceKey) {
//        return dataSourceMap.containsKey(datasourceKey);
//    }
//}
