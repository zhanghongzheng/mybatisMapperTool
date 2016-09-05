//package com.hydra.tool.zookeeper.curator.listener;
//
//import com.hydra.tool.zookeeper.curator.NodeUtil;
//import com.hydra.tool.zookeeper.curator.ann.NodeMonitor;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//
///**
// * Created by lee on 15/12/1.
// */
//@Aspect
//public class ListenerAspect {
//    @Around(
//            value = "execution(* *(..)) && @annotation(listener)",
//            argNames = "pjp,listener"
//    )
//    public Object doListener(ProceedingJoinPoint pjp, NodeMonitor listener) throws Throwable {
//        Object obj=null;
//        try {
//
//        }finally {
//            NodeUtil.changeNode("/master");
//            obj=pjp.proceed();
//        }
//        return obj;
//    }
//}
