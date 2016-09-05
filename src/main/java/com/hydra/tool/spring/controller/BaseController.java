//package com.hydra.tool.spring.controller;
//
//import com.hydra.tool.exception.ParamsAuthException;
//import com.hydra.tool.security.SignAuthUtil;
//import com.hydra.tool.web.ajax.AjaxUtil;
//import org.apache.log4j.Logger;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//import java.sql.Timestamp;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//
///**
// * Created by administrator on 14-3-9.
// */
//public abstract class BaseController {
//    protected static final Logger LOG = Logger.getLogger(BaseController.class);
//    private ExecutorService exec = Executors.newCachedThreadPool();
//
//    public void sendAjaxRespond(Object object, String tip) {
//        try {
//            AjaxUtil.sendAjaxRespond(getResponse(), object, tip);
//        } catch (IOException e) {
//            LOG.error("io error", e);
//        }
//    }
//
//    public void sendAjaxErrorRespond(int errorCode, String tip) {
//        try {
//            AjaxUtil.sendAjaxErrorRespond(getResponse(), errorCode, tip);
//        } catch (IOException e) {
//            LOG.error("io error", e);
//        }
//    }
//
//    public <T> T[] getArray(String name) {
//        Object obj = getRequest().getParameterMap().get(name);
//        if (obj != null) {
//            return (T[]) obj;
//        }
//        return null;
//    }
//
//    public String getString(String name) {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        return request.getParameter(name);
//    }
//
//    public Integer getInteger(String name) {
//        return Integer.parseInt(getRequest().getParameter(name));
//    }
//
//    public Long getLong(String name) {
//        return Long.parseLong(getRequest().getParameter(name));
//    }
//
//    public Float getFloat(String name) {
//        return Float.parseFloat(getRequest().getParameter(name));
//    }
//
//    public Double getDouble(String name) {
//        return Double.parseDouble(getRequest().getParameter(name));
//    }
//
//    public Timestamp getDate(String name) {
//        return Timestamp.valueOf(getRequest().getParameter(name));
//    }
//
//    public HttpServletRequest getRequest() {
//        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//    }
//
//    public HttpServletResponse getResponse() {
//        return (HttpServletResponse) getRequest().getAttribute("response");
//    }
//
//    public HttpSession getSession() {
//        return getRequest().getSession();
//    }
//
//    public int page() {
//        int page = 1;
//        try {
//            page = getInteger("page");
//        } catch (Exception ignored) {
//        }
//        return page;
//    }
//
//    public String getAuthString(String name) throws ParamsAuthException {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        return SignAuthUtil.getValue(getString("auth"), request.getParameter(name));
//    }
//
//    public Integer getAuthInteger(String name) throws ParamsAuthException {
//        return Integer.parseInt(SignAuthUtil.getValue(getString("auth"), getRequest().getParameter(name)));
//    }
//
//    public Long getAuthLong(String name) throws ParamsAuthException {
//        return Long.parseLong(SignAuthUtil.getValue(getString("auth"), getRequest().getParameter(name)));
//    }
//
//    public Float getAuthFloat(String name) throws ParamsAuthException {
//        return Float.parseFloat(SignAuthUtil.getValue(getString("auth"), getRequest().getParameter(name)));
//    }
//
//    public Double getAuthDouble(String name) throws ParamsAuthException {
//        return Double.parseDouble(SignAuthUtil.getValue(getString("auth"), getRequest().getParameter(name)));
//    }
//
//    public Timestamp getAuthDate(String name) throws ParamsAuthException {
//        return Timestamp.valueOf(SignAuthUtil.getValue(getString("auth"), getRequest().getParameter(name)));
//    }
//
//
//    public int authPage() throws ParamsAuthException {
//        int page = 1;
//        try {
//            page = getAuthInteger("page");
//        } catch (NullPointerException ignored) {
//        }
//        return page;
//    }
//
//
//    public ExecutorService exec() {
//        return exec;
//    }
//
//    public void setError(String error) {
//        getRequest().setAttribute("error", error);
//    }
//}
