package com.hydra.tool.spring.datasourceroute;

/**
 * Created by ZhengGong on 15/6/16.
 * Description
 */
public class DataSourceHolder {
    private static final ThreadLocal<String> dataSourceLocal = new ThreadLocal<String>();

    public DataSourceHolder() {
    }

    public static String getDataSource() {
        return dataSourceLocal.get();
    }

    public static void setDataSource(String dataSourceKey) {
        dataSourceLocal.set(dataSourceKey);
    }
}
