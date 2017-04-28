package com.cheng.devtool;

import com.cheng.devtool.metadata.EntityAnalyzer;
import com.cheng.devtool.metadata.EntityMeta;
import com.cheng.devtool.mybatis.GeneratorEntity;
import com.cheng.devtool.template.FreemarkerFactory;
import com.cheng.devtool.util.Config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println("GeneratorService.run");
        List<EntityMeta> entityMetas = EntityAnalyzer.build();
        for (EntityMeta entityMeta : entityMetas) {
            FreemarkerFactory.write("Service.ftl",entityMeta, Config.getServicePath()+
                    entityMeta.getPascalName()+"Service.java");
            FreemarkerFactory.write("Controller.ftl",entityMeta, Config.getControllerPath()+
                    entityMeta.getPascalName()+"Controller.java");

            String templatesDir = Config.getProjectTemplatesPath() +
                    entityMeta.getFlatName() + "/";
            FreemarkerFactory.write("list.ftl",entityMeta, templatesDir + "list.ftl");
            FreemarkerFactory.write("add.ftl",entityMeta, templatesDir+ "add.ftl");
            FreemarkerFactory.write("edit.ftl",entityMeta, templatesDir+ "edit.ftl");
            FreemarkerFactory.write("view.ftl",entityMeta, templatesDir+ "view.ftl");
        }
        Map<String,Object> t = new HashMap<>();
        t.put("l",entityMetas);
        FreemarkerFactory.write("menu.ftl",t, Config.getProjectTemplatesPath()+
                "menu.ftl");
        System.out.println("GeneratorService.done");
    }


}
