package com.hydra.tool.verification;


import com.hydra.tool.object.ObjectUtil;

import java.util.Map;

/**
 * Created by ZhengGong on 15/6/19.
 * Description Map数据验证抽象类
 */
public abstract class AbstractMapVerification {

    /**
     * object检测
     *
     * @param data    元数据
     * @param name    key
     * @param clazzes 是否是所需的class
     * @throws VerificationException 验证错误异常
     */
    public static void checkObject(Map<String, Object> data, String name, Class<?>... clazzes) throws VerificationException {
        Object o = data.get(name);

        if (ObjectUtil.isNotNull(o)) {
            if (ObjectUtil.isNotNull(clazzes)) {
                for (int i = 0; i < clazzes.length; i++) {
                    if (clazzes[i].isInstance(o)) {
                        return;
                    }
                }
            } else {
                return;
            }
        }
        throw new VerificationException(name);
    }

    /**
     * string检测
     *
     * @param data 元数据
     * @param name key
     * @throws VerificationException 验证错误异常
     */
    public static void checkString(Map<String, Object> data, String name) throws VerificationException {
        Object o = data.get(name);

        if (ObjectUtil.isNotNull(o) && String.class.isInstance(o)) {
            return;
        }
        throw new VerificationException(name);
    }

    /**
     * map检测
     *
     * @param data 元数据
     * @throws VerificationException 验证错误异常
     */
    public static void checkMap(Map<String, Object> data) throws VerificationException {
        if (ObjectUtil.isNotEmpty(data)) {
            return;
        }
        throw new VerificationException("map");
    }

    public static class VerificationException extends Exception {
        private String name;

        public VerificationException(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
