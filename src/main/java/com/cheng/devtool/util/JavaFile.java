package com.cheng.devtool.util;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月26日
 */
public class JavaFile {

    public static List<String> startWith(String dir, String lowerFileName,String startStr){
        return startWith(getFilePath(dir,lowerFileName),startStr);
    }

    public static List<String> startWith(Path filePath,String startStr){
        try {
            return Files.readAllLines(filePath).stream()
                    .filter(s->s.trim().startsWith(startStr))
                    .map(s -> s.trim().replaceAll(startStr,"").replaceAll(";",""))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> contains(String dir, String lowerFileName,String startStr){
        return contains(getFilePath(dir,lowerFileName),startStr);
    }

    public static List<String> contains(Path filePath,String containsStr){
        try {
            return Files.readAllLines(filePath).stream()
                    .filter(s->s.trim().contains(containsStr))
                    .map(s -> s.trim().replaceAll(";",""))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Path getFilePath(String dir, String lowerFileName) {
        File file = new File(dir);
        File[] files = file.listFiles((dir1, name) -> name.toLowerCase().equals(lowerFileName+".java"));
        if (null == files || files.length < 1) {
            return null;
        }
        return Paths.get(files[0].getPath());
    }

    public static String getClassName(String dir, String lowerFileName){
        Path filePath = getFilePath(dir, lowerFileName);
        File file = new File(filePath.toUri());
        return file.getName().replaceAll(".java","");
    }

}
