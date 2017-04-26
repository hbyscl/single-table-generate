package com.cheng.devtool;

import com.cheng.devtool.mybatis.GeneratorEntity;
import com.cheng.devtool.template.FreemarkerFactory;
import com.cheng.devtool.util.PropertiesUtil;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月21日
 * @since soter 1.0.0
 */
public class GeneratorService {

    public static void main(String[] args) throws Exception {
        run();
    }


    public static void run() throws Exception {
        GeneratorEntity.run();
        String project = PropertiesUtil.getProperties("project.path");
        String $package = PropertiesUtil.getProperties("project.package");
        String entity = $package + ".entity";
        File file = new File(project + "/" + entity.replace(".", "/"));
        File[] entityJavaFiles = file.listFiles((dir, name) -> !name.endsWith("Example.java"));
        for (File entityJavaFile : entityJavaFiles) {
            Map<String, Object> var = new HashMap<>();
            var.put("package", $package);

            String fileName = entityJavaFile.getName();
            List<String> $fields = Files.readAllLines(entityJavaFile.toPath())
                    .stream().filter(s -> s.trim().startsWith("private "))
                    .map(s -> strFirstUp(
                            s.trim()
                                    .replaceAll("private ", "")
                                    .replaceAll(";", "")
                                    .split(" ")[1])
                    )
                    .collect(Collectors.toList());
            var.put("fields", $fields);
            String $entity = fileName.replace(".java", "");
            var.put("entity", $entity);
            String $entityObj = $entity.substring(0, 1).toLowerCase() + $entity.substring(1);
            var.put("entityObj", $entityObj);
            setPk($entity,var);
            FreemarkerFactory.write("Service.ftl",var,
                    project + "/" + $package.replace(".", "/")+"/service/basic/"+$entity+"Service.java");
        }
    }




    private static void setPk(String entityName,Map<String, Object> var) throws Exception {
        String project = PropertiesUtil.getProperties("project.path");
        String $package = PropertiesUtil.getProperties("project.package");
        String basicDao = $package + ".dao.basic";
        String pkFiled = Files.readAllLines(Paths.get(project + "/" + basicDao.replace(".", "/") + "/" + entityName + "Mapper.java"))
                .stream().filter(s -> s.contains("selectByPrimaryKey"))
                .map(s -> s.split("selectByPrimaryKey")[1]
                        .replaceAll("\\(", "")
                        .replaceAll("\\);", ""))
                .findFirst().get();
        String[] split = pkFiled.split(" ");
        var.put("pkType", split[0]);
        String pk = split[1];
        var.put("pk", pk);
        var.put("pkUp", pk.substring(0,1).toUpperCase()+pk.substring(1));
        System.out.println("var.get(\"pkUp\") = "+entityName+"---" + var.get("pkUp"));
    }

    private static String strFirstUp(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }


}
