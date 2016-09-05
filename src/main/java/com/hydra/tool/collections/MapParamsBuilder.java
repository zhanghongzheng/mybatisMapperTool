package com.hydra.tool.collections;

import com.google.common.collect.Maps;

import java.util.Map;

/**
 * naccount Created by ZhengGong on 15/9/28.
 * PackageName com.mls.pay.naccount.common.utils
 * Description Map参数快速构建器
 */
public class MapParamsBuilder {
    private Map<String, Object> params = Maps.newHashMap();

    public static MapParamsBuilder create(String key, Object value) {
        return new MapParamsBuilder().add(key, value);
    }

    public MapParamsBuilder add(String key, Object value) {
        params.put(key, value);
        return this;
    }

    public MapParamsBuilder addAll(Map<String, Object> map) {
        params.putAll(map);
        return this;
    }

    public Map<String, Object> build() {
        return params;
    }
}
