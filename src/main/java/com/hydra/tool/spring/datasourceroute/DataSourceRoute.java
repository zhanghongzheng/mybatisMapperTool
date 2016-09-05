//package com.hydra.tool.spring.datasourceroute;
//
//import com.hydra.tool.spring.datasourceroute.config.Configs;
//import org.apache.log4j.Logger;
//import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
//
//import javax.annotation.PostConstruct;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Created by ZhengGong on 15/6/16.
// * Description 数据库路由
// */
//public class DataSourceRoute extends AbstractRoutingDataSource {
//    private static final Logger LOG = Logger.getLogger(DataSourceRoute.class);
//    private Object defaultDataSource;
//    private Object bakDataSource;
//    private Timer timer = new Timer();
//
//    public DataSourceRoute() {
//        LOG.debug("DatasourceRoute load");
//        this.setTargetDataSources(Configs.getDatasources());
//        this.defaultDataSource = Configs.getDefaultDataSource();
//        this.bakDataSource = Configs.getBakDataSource();
//        this.setDefaultTargetDataSource(this.defaultDataSource);
//        this.openHearbeat(Configs.isHeartbeat(), Configs.getHeartbeatSQL(), Configs.getHeartbeatDelay(), Configs.getHeartbeatTime());
//        LOG.info("load dataSources [" + Configs.getDatasources() + "],defaultDataSource[" + this.defaultDataSource + "],bak DataSource[" + this.bakDataSource + "],heartbeat[" + Configs.isHeartbeat() + "],heartbeatSQL[" + Configs.getHeartbeatSQL() + "],heartbeatTime[" + Configs.getHeartbeatTime() + "]");
//    }
//
//    public void openHearbeat(boolean isOpen, final String heartbeatSQL, long heartbeatDelay, long heartbeatTimePeriod) {
//        if (isOpen && this.timer != null) {
//            this.timer.cancel();
//            this.timer.schedule(new TimerTask() {
//                public void run() {
//                    try {
//                        if (null == heartbeatSQL || "".equals(heartbeatSQL.trim())) {
//                            return;
//                        }
//
//                        DataSourceRoute.LOG.debug("heart beat SQL begin:" + heartbeatSQL);
//                        DataSourceRoute.this.getConnection().createStatement().execute(heartbeatSQL);
//                        DataSourceRoute.LOG.debug("heart beat SQL end:" + heartbeatSQL);
//                    } catch (Exception var2) {
//                        DataSourceRoute.LOG.error("heart beat SQL ERROR. dynamic switch dataSource", var2);
//                        DataSourceRoute.this.switchDataSource();
//                    }
//
//                }
//            }, heartbeatDelay, heartbeatTimePeriod);
//        }
//
//    }
//
//    private void switchDataSource() {
//        LOG.info("switch dataSource begin: defaultDataSource[" + this.defaultDataSource + "],bakDataSource" + this.bakDataSource + "]");
//        Object temp = this.defaultDataSource;
//        this.defaultDataSource = this.bakDataSource;
//        this.bakDataSource = temp;
//        this.setDefaultTargetDataSource(this.defaultDataSource);
//        LOG.info("switch dataSource end: defaultDataSource[" + this.defaultDataSource + "],bakDataSource" + this.bakDataSource + "]");
//    }
//
//    protected Object determineCurrentLookupKey() {
//        LOG.info("determineCurrentLookupKey:[" + DataSourceHolder.getDataSource() + "]");
//        return DataSourceHolder.getDataSource();
//    }
//
//    @PostConstruct
//    public void init() {
//        Configs.init();
//    }
//}
