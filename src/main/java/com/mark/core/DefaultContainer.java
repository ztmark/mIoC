package com.mark.core;

import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.mark.annotation.Bean;
import com.mark.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Author: Mark
 * Date  : 15/11/30.
 */
public class DefaultContainer implements Container {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultContainer.class);
    private ConcurrentHashMap<String, Object> container = new ConcurrentHashMap<>();


    public DefaultContainer(String... basePackage) {
        for (String p : basePackage) {
            scanPackage(p.trim());
        }
    }

    private void scanPackage(String basePackage) {
        try {
            Enumeration<URL> urls = DefaultContainer.class.getClassLoader().getResources(basePackage.replace(".", "/"));
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                String protocol = url.getProtocol();
                if (protocol.equals("file")) {
                    File path = new File(url.getPath());
                    File[] subFiles = path.listFiles(file -> file.isFile() && file.getName().endsWith(".class") || file.isDirectory());
                    for (File subFile : subFiles) {
                        if (subFile.isFile()) {
                            String fileName = subFile.getName();
                            String clsName = Files.getNameWithoutExtension(fileName);
                            String fullClsName = clsName;
                            if (!Strings.isNullOrEmpty(basePackage)) {
                                fullClsName = basePackage + "." + clsName;
                            }
                            Class<?> cls = Utils.loadClass(fullClsName);
                            if (cls.isAnnotationPresent(Bean.class)) {
                                Object obj = Utils.newInstance(cls);
                                Bean bean = cls.getAnnotation(Bean.class);
                                if (Strings.isNullOrEmpty(bean.name())) {
                                    container.put(clsName, obj);
                                } else {
                                    container.put(bean.name(), obj);
                                }
                            }

                        } else {
                            scanPackage(basePackage + "." + subFile.getName());
                        }
                    }
                }
            }
        } catch (IOException e) {
            LOGGER.error("load classes failed.", e);
            throw new RuntimeException("load classes failed.", e);
        }
    }

    @Override
    public <T> T getBeanByName(String name, Class<T> type) {
        //noinspection unchecked
        return (T) container.get(name);
    }
}
