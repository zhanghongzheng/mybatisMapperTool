/**
 * Copyright © 2014 meilishuo.com All rights reserved.
 */
package com.hydra.tool.config;

import com.google.common.base.Strings;
import com.google.common.collect.Maps;
import com.hydra.tool.string.StrUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.URL;
import java.util.Map;
import java.util.Properties;

/**
 *  Configs.java
 *  hydra
 *  com.hydra.tool.config
 */
public final class ConfigUtil {

    private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);

    private static File[] findConfigFiles(String folderPath) {
        URL url = ConfigUtil.class.getClassLoader().getResource(folderPath);
        if (url == null) {
            return new File[]{};
        }
        if (Strings.isNullOrEmpty(url.getPath())) return new File[]{};
        File folder = new File(url.getPath());
        return folder.listFiles();
    }

    public static Map<String, String> getConfigsByFile(String folderPath, String filename) {
        if (StrUtil.isBlank(filename)) {
            return Maps.newHashMap();
        }
        File[] files = findConfigFiles(folderPath);
        for (File f : files) {
            String fName = f.getName();
            if (fName.substring(0, fName.lastIndexOf(".")).equals(filename)) {
                return loadFileToMap(f);
            }
        }
        return Maps.newHashMap();
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private static Map<String, String> loadFileToMap(File file) {
        InputStream in = null;
        try {
            in = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(new InputStreamReader(in, "UTF-8"));
            Map<String, String> map = Maps.newHashMap();
            map.putAll((Map) prop);
            return map;
        } catch (Exception e) {
            logger.error("load error {}", e);
        } finally {
            try {
                if (in != null) in.close();
            } catch (IOException e) {
                logger.error("load IOException {}", e);
            }
        }
        return Maps.newHashMap();
    }
}
