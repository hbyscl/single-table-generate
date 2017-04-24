package com.cheng.devtool;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月21日
 * @since soter 1.0.0
 */
public class GeneratorServiceController {
    public static void main(String[] args) throws Exception {
        GeneratorEntity.run();
        String project = PropertiesUtil.getProperties("project.path");
        String entity = PropertiesUtil.getProperties("project.package")+".entity";

        File file = new File(project + "/" + entity.replace(".", "/"));
        File[] entityJavaFiles = file.listFiles((dir, name) -> !name.endsWith("Example.java"));
        for (File entityJavaFile : entityJavaFiles) {

            List<String> filedList = Files.readAllLines(entityJavaFile.toPath())
                    .stream().filter(s -> s.trim().startsWith("private "))
                    .map(s->s.trim().replaceAll("private ","").replaceAll(";",""))
                    .collect(Collectors.toList());
            filedList.forEach(System.out::println);
        }
    }


}
