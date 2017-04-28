package com.cheng.devtool.mybatis;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月21日
 * @since soter 1.0.0
 */
public class GeneratorEntity {

    public static void run() throws Exception {
        GeneratorXml.run();
        System.out.println("GeneratorEntity.run");
        List<String> warnings = new ArrayList<>();
        MyBatisGenerator myBatisGenerator = null;
        Configuration configuration = null;
        ConfigurationParser parser = new ConfigurationParser(warnings);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        File file = new File(GeneratorEntity.class.getResource("/mybatisGenerator.xml").toURI());
        configuration = parser.parseConfiguration(file);
        myBatisGenerator = new MyBatisGenerator(configuration, callback, warnings);
        myBatisGenerator.generate(new ProgressCallback() {
            @Override
            public void introspectionStarted(int i) {

            }

            @Override
            public void generationStarted(int i) {

            }

            @Override
            public void saveStarted(int i) {

            }

            @Override
            public void startTask(String s) {
                System.out.println("GeneratorEntity.startTask");
            }

            @Override
            public void done() {

            }

            @Override
            public void checkCancel() throws InterruptedException {

            }
        });
        System.out.println("GeneratorEntity.done");
    }

    public static void main(String[] args) throws Exception{
        run();
    }
}
