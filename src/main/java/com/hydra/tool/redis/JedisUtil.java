package com.hydra.tool.redis;

import com.hydra.tool.config.ConfigUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;

/**
 * Created by ZhengGong on 15/9/7.
 * Description
 */
public class JedisUtil {

    private static JedisUtil instance;

    private static String MAX_ACTIVE_NAME = "jedis.maxActive";
    private static String MAX_IDLE_NAME = "jedis.maxIdle";
    private static String TEST_ON_BORROW_NAME = "jedis.testOnBorrow";
    private static String HOST_NAME = "jedis.host";
    private static String PORT_NAME = "jedis.port";
    private static String TIMEOUT_NAME = "jedis.timeout";

    //可用连接实例的最大数目，默认值为8；
    private static int MAX_ACTIVE = 1024;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private static int MAX_IDLE = 200;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private static boolean TEST_ON_BORROW = true;

    private static JedisPool jedisPool = null;

    private static volatile boolean isStart = false;

    /**
     * 获取Jedis实例
     *
     * @return
     */
    public synchronized static Jedis getJedis() {
        init();

        try {
            if (jedisPool != null) {
                return jedisPool.getResource();
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void init() {
        if (!isStart) {
            try {
                Map<String, String> configMap = ConfigUtil.getConfigsByFile("jedis", "jedis");

                JedisPoolConfig config = new JedisPoolConfig();

                config.setMaxTotal(MAX_ACTIVE);
                config.setMaxIdle(MAX_IDLE);
                config.setTestOnBorrow(TEST_ON_BORROW);
                jedisPool = new JedisPool(config, configMap.get(HOST_NAME), Integer.valueOf(configMap.get(PORT_NAME)), Integer.valueOf(configMap.get(TIMEOUT_NAME)));
                isStart = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
