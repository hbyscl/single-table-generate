package com.cheng.devtool.template;

import com.cheng.devtool.metadata.FieldMeta;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.util.Locale;
import java.util.Map;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月25日
 */
public class FreemarkerFactory {

    private static Configuration configuration;

    private static void init() throws Exception {
        configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setLocale(Locale.CHINA);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINA, "utf-8");
        configuration.setDirectoryForTemplateLoading(
                new File(FreemarkerFactory.class.getResource("/template").toURI())
        );
    }

    public static void write(String templateName, Object var, String targetFile) throws Exception {
        if (null == configuration) {
            init();
        }
        System.out.println("FreemarkerFactory.write:"+targetFile);
        Template template = configuration.getTemplate(templateName);

        try (FileWriter out = new FileWriter(targetFile)) {
            template.process(var, out);
            out.flush();
        }

    }

    public static void main(String[] args) throws Exception {
        FieldMeta fieldMeta = new FieldMeta();
        write("test.ftl",fieldMeta,"d:/a.txt");
    }

}
