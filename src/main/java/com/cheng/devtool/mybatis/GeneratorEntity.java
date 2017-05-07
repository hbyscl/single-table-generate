package com.cheng.devtool.mybatis;

import com.cheng.devtool.util.Config;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        myBatisGenerator.generate(null);
        System.out.println("GeneratorEntity.done");

        addAtMapper();
    }

    private static void addAtMapper() {
        File mapperDir = new File(Config.getDaoPath());
        List<String> tables = Config.getTables().stream().map(s ->  s.replaceAll("_","")).collect(Collectors.toList());
        File[] mappers = mapperDir.listFiles((dir, name) ->
                tables.contains(
                        name.replaceAll("Mapper.java", "").toLowerCase()
                )
        );
        for (File mapper : mappers) {
            System.out.println("mapper.getName() = " + mapper.getName());
            try {
                List<String> codes = new ArrayList<>();
                Path path = mapper.toPath();
                Files.readAllLines(path).forEach(s->{
                    if(s.startsWith("public interface ")){
                        codes.add("@org.apache.ibatis.annotations.Mapper");
                    }
                    codes.add(s);
                });
                Files.write(path,codes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        run();
    }
}
