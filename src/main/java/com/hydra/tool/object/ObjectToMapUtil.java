package com.hydra.tool.object;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Created by ZhengGong on 15/3/14.
 * Description 用于object和map之间的转化
 */
public final class ObjectToMapUtil {
    private static final Logger LOG = LoggerFactory.getLogger(ObjectToMapUtil.class);

    public static <T> OMBean create(Class<T> clazz) {
        return new OMBean<T>(clazz);
    }

    // 用于转化的工具bean
    public static class OMBean<T> {
        private Class<T> clazz;

        private OMBean(Class<T> clazz) {
            this.clazz = clazz;
        }

        public Map<String, Object> toMap(T t) {
            Map<String, Object> map = Maps.newHashMap();
            if (ObjectUtil.isNull(t)) {
                return map;
            }

            // 转化
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor property : propertyDescriptors) {
                    // 获取
                    String key = property.getName();
                    //过滤class属性，代理对象的callback callbacks
                    if (!key.equals("class") && !key.equals("callback") && !key.equals("callbacks")) {
                        // 得到property对应的getter方法
                        Method getter = property.getReadMethod();
                        Object value = getter.invoke(t);
                        map.put(key, value);
                    }
                }
                return map;
            } catch (Exception e) {
                LOG.error("object to map Error " + e);
                return Maps.newHashMap();
            }
        }

        public T toObject(Map<String, Object> map) {
            if (ObjectUtil.isEmpty(map)) {
                return null;
            }

            // 创建实体
            T t;
            try {
                t = clazz.newInstance();
            } catch (Exception e) {
                LOG.error("create object " + clazz.getName() + "error", e);
                return null;
            }

            // 向对象赋值
            try {
                BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

                for (PropertyDescriptor property : propertyDescriptors) {
                    String key = property.getName();
                    // 得到property对应的getter方法
                    if (map.containsKey(key)) {
                        try {
                            property.getWriteMethod().invoke(t, numberTypeChange(property.getPropertyType(), map.get(key)));
                        } catch (Exception e) {
                            LOG.error("write " + clazz.getName() + " " + key + "error", e);
                        }
                    }
                }
            } catch (IntrospectionException e) {
                LOG.error("get " + clazz.getName() + "beanInfo error", e);
            }

            return t;
        }

    }

    private static Object numberTypeChange(Class<?> clazz, Object object) {
        Object value = object;
        if (ObjectUtil.isNotNull(object)) {
            if (clazz == Byte.class || clazz.getName().equals("byte")) {
                value = Byte.valueOf(object + "");
            } else if (clazz == Short.class || clazz.getName().equals("short")) {
                value = Short.valueOf(object + "");
            } else if (clazz == Integer.class || clazz.getName().equals("int")) {
                value = Integer.valueOf(object + "");
            } else if (clazz == Long.class || clazz.getName().equals("long")) {
                value = Long.valueOf(object + "");
            } else if (clazz == Float.class || clazz.getName().equals("float")) {
                value = Float.valueOf(object + "");
            } else if (clazz == Double.class || clazz.getName().equals("double")) {
                value = Double.valueOf(object + "");
            } else if (clazz == String.class) {
                value = object + "";
            }
        }
        return value;
    }

    public static Map<String, Object> keep(Map<String, Object> data, String... keys) {
        Map<String, Object> keepData = Maps.newHashMap();
        if (ObjectUtil.isNotEmpty(keys)) {
            for (int i=0; i<keys.length; i++) {
                keepData.put(keys[i], data.get(keys[i]));
            }
        }
        return keepData;
    }
}
