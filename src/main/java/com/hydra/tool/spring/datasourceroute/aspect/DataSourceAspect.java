//package com.hydra.tool.spring.datasourceroute.aspect;
//
//import com.hydra.tool.spring.datasourceroute.DataSourceHolder;
//import com.hydra.tool.spring.datasourceroute.annotation.DateSourceSwitch;
//import com.hydra.tool.spring.datasourceroute.config.Configs;
//import org.apache.log4j.Logger;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//
///**
// * Created by ZhengGong on 15/6/16.
// * Description
// */
//@Aspect
//@EnableAspectJAutoProxy
//@Configuration
//public class DataSourceAspect {
//    private static Logger LOG = Logger.getLogger(DataSourceAspect.class);
//
//    public DataSourceAspect() {
//    }
//
//    @Around("@annotation(dateSourceSwitch)")
//    public Object doAround(ProceedingJoinPoint pjp, DateSourceSwitch dateSourceSwitch) throws Throwable {
//        String currentDataSourceName = null;
//
//        try {
//            currentDataSourceName = DataSourceHolder.getDataSource();
//            DataSourceHolder.setDataSource(dateSourceSwitch.value());
//            LOG.info("markDataSource:" + dateSourceSwitch.value());
//            if (!Configs.containsKey(dateSourceSwitch.value())) {
//                LOG.info("dataSource not contains:" + dateSourceSwitch.value());
//            }
//        } catch (Exception ignored) {
//
//        }
//
//        Object e;
//        try {
//            e = pjp.proceed();
//        } finally {
//            try {
//                DataSourceHolder.setDataSource(currentDataSourceName);
//            } catch (Exception ignored) {
//
//            }
//
//        }
//
//        return e;
//    }
//}
