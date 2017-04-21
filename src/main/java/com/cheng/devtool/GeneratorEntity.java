package com.cheng.devtool;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.api.ProgressCallback;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月21日
 * @since soter 1.0.0
 */
public class GeneratorEntity {

    public static void run(){
        List<String> warnings = new ArrayList<>();
        MyBatisGenerator myBatisGenerator = null;
        Configuration configuration = null;
        ConfigurationParser parser = new ConfigurationParser(warnings);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        try {
            File file = new File(GeneratorEntity.class.getResource("/mybatisGenerator.xml").toURI());
            configuration = parser.parseConfiguration(file);
            myBatisGenerator = new MyBatisGenerator(configuration,callback,warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
