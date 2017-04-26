package com.cheng.devtool.util;

import com.cheng.devtool.util.PropertiesUtil;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月26日
 */
public class Config {

    public static String getProjectPath(){
        return PropertiesUtil.getProperties("project.path");
    }

    public static String getEntityPath(){
        return getPackagePath()+"entity/";
    }

    public static String getDaoPath(){
        return getPackagePath()+"dao/basic/";
    }

    public static String getServicePath(){
        return getPackagePath()+"service/basic/";
    }

    public static String getControllerPath(){
        return getPackagePath()+"controller/";
    }

    public static String getPackage(){
        return PropertiesUtil.getProperties("project.package");
    }

    public static String getPackagePath(){
        return getProjectPath() + "/" + getPackage().replace(".", "/")+"/";
    }

    public static List<String> getTables(){
        return Stream.of(PropertiesUtil.getProperties("tables").split(","))
                .filter(s->null != s && !"".equals(s.trim()))
                .collect(Collectors.toList());
    }

}
