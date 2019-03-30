package com.pay.common.utils;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
 * @desc 获取配置文件 application.properties里面的内容
 */
public class ConstantUtils {
    private static Properties props;
    static {
        loadProps();
    }
    synchronized static private void loadProps() {
        System.out.println("start to load properties.......");
        props = new Properties();
        InputStream in = null;
        try {

            in = ConstantUtils.class.getClassLoader().getResourceAsStream("application.properties");
            props.load(in);
        } catch (Exception e) {

        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {

            }
        }
        System.out.println("load properties over...........");
    }
    public static String getProperty(String key) {
        if (null == props) {
            loadProps();
        }
        return props.getProperty(key);
    }

    public static void main(String[] args) {
        System.out.println(ConstantUtils.getProperty("spring.env.config"));
    }
}
