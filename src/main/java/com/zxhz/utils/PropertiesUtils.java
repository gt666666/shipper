package com.zxhz.utils;

import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 类描述：
 * 创建作者：gt
 * 创建日期 ： 2019/12/3
 */
public class PropertiesUtils {
    public Properties properties;
    public Properties propertiesCustom;
    private static PropertiesUtils propertiesUtils = null;

    private   PropertiesUtils() {
        this.properties = new Properties();
        this.propertiesCustom = new Properties();
        InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream("custom/custom.properties");
        try {
            properties.load(in);
            String value = properties.getProperty("profiles.active");
            if (!StringUtils.isEmpty(value)) {
                InputStream cin = PropertiesUtils.class.getClassLoader().getResourceAsStream("custom/custom-" + value + ".properties");
                propertiesCustom.load(cin);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropertiesUtils getInstance() {
        if (propertiesUtils == null) {
            return propertiesUtils = new PropertiesUtils();
        }
        return propertiesUtils;
    }
}
