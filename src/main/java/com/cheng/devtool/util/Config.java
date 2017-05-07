package com.cheng.devtool.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月26日
 */
public class Config {

    public static String getProjectJavaPath() {
        return PropertiesUtil.getProperties("project.path.java");
    }

    public static String getProjectTemplatesPath() {
        return PropertiesUtil.getProperties("project.path.templates");
    }

    public static String getProjectstaticPath() {
        return PropertiesUtil.getProperties("project.path.static");
    }

    public static String getEntityPath() {
        return getPackagePath() + "entity/";
    }

    public static String getDaoPath() {
        return getPackagePath() + "dao/basic/";
    }

    public static String getServicePath() {
        String s = getPackagePath() + "service/";
        Path path = Paths.get(s);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public static String getServiceImplPath(){
        String s = getServicePath() + "impl/";
        Path path = Paths.get(s);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return s;
    }

    public static String getControllerPath() {
        String s = getPackagePath() + "controller/";
        Path path = Paths.get(s);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return s;
    }

    public static String getPackage() {
        return PropertiesUtil.getProperties("project.package");
    }

    public static String getPackagePath() {
        return getProjectJavaPath() + "/" + getPackage().replace(".", "/") + "/";
    }

    public static List<String> getTables() {
        return Stream.of(PropertiesUtil.getProperties("tables").split(","))
                .filter(s -> null != s && !"".equals(s.trim()))
                .collect(Collectors.toList());
    }

}
