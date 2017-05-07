package com.cheng.devtool;

import com.cheng.devtool.metadata.EntityAnalyzer;
import com.cheng.devtool.metadata.EntityMeta;
import com.cheng.devtool.mybatis.GeneratorEntity;
import com.cheng.devtool.template.FreemarkerFactory;
import com.cheng.devtool.util.Config;

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

    private static List<EntityMeta> entityMetas = null;

    public static void main(String[] args) throws Exception {
        dao();
        service();
        page();
        menu();
    }

    private static void dao() throws Exception {
        GeneratorEntity.run();
    }

    private static void service() throws Exception {
        List<EntityMeta> metas = getMetas();
        for (EntityMeta entityMeta : metas) {
            FreemarkerFactory.write("Service.ftl", entityMeta, Config.getServicePath() +
                    entityMeta.getPascalName() + "Service.java");
            FreemarkerFactory.write("ServiceImpl.ftl", entityMeta, Config.getServiceImplPath() +
                    entityMeta.getPascalName() + "ServiceImpl.java");
            FreemarkerFactory.write("Controller.ftl", entityMeta, Config.getControllerPath() +
                    entityMeta.getPascalName() + "Controller.java");
        }
    }

    private static void page() throws Exception {
        List<EntityMeta> metas = getMetas();
        for (EntityMeta entityMeta : metas) {

            String templatesDir = Config.getProjectTemplatesPath() +
                    entityMeta.getFlatName() + "/";
            FreemarkerFactory.write("list.ftl", entityMeta, templatesDir + "list.ftl");
            FreemarkerFactory.write("add.ftl", entityMeta, templatesDir + "add.ftl");
            FreemarkerFactory.write("edit.ftl", entityMeta, templatesDir + "edit.ftl");
            FreemarkerFactory.write("view.ftl", entityMeta, templatesDir + "view.ftl");
        }
    }

    private static void menu() throws Exception {
        Map<String, Object> t = new HashMap<>();
        t.put("l", getMetas());
        FreemarkerFactory.write("menu.ftl", t, Config.getProjectTemplatesPath() +
                "menu.ftl");
        System.out.println("GeneratorService.done");
    }

    private static List<EntityMeta> getMetas() {
        if (null == entityMetas) {
            entityMetas = EntityAnalyzer.build().stream()
                    .filter(m -> !m.getTableName().startsWith("ref"))
                    .collect(Collectors.toList());
        }
        return entityMetas;
    }

}
