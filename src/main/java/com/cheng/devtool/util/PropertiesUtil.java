package com.cheng.devtool.util;

import java.util.ResourceBundle;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月21日
 * @since soter 1.0.0
 */
public class PropertiesUtil {
    private static ResourceBundle config = null;

    public static void initProperties(){
        config = ResourceBundle.getBundle("init");
    }

    public static String getProperties(String key){
        if(null == config){
            initProperties();
        }
        return config.getString(key);
    }
}
