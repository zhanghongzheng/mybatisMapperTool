package com.hydra.tool.nutz;

import com.hydra.tool.config.ConfigUtil;
import org.nutz.dao.Dao;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.SimpleDataSource;

import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Created by ZhengGong on 15/6/4.
 * Description nutz dao初始化
 */
public class NutzHelper {
    private final NutDao dao = new NutDao();
    private static final Map<String, String> config = ConfigUtil.getConfigsByFile("nutz", "nutz");
    private static final NutzHelper INSTANCE = new NutzHelper();
    private static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    private void init() {
        SimpleDataSource dataSource = new SimpleDataSource();
        try {
            dataSource.setDriverClassName(config.get("nutzdb.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("init dataSource error");
        }
        dataSource.setJdbcUrl(config.get("nutzdb.url"));
        dataSource.setUsername(config.get("nutzdb.username"));
        dataSource.setPassword(config.get("nutzdb.password"));
        dao.setDataSource(dataSource);
    }

    public static NutzHelper getInstance() {
        if (atomicBoolean.get()) {
            return INSTANCE;
        } else {
            synchronized (NutzHelper.class) {
                if (atomicBoolean.get()) {
                    return INSTANCE;
                } else {
                    INSTANCE.init();
                    atomicBoolean.set(true);
                }
            }
        }
        return INSTANCE;
    }

    public Dao getDao() {
        return dao;
    }
}
