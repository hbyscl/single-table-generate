package com.cheng.devtool;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月25日
 */
public class FreemarkerUtil {

    private static Configuration configuration;

    private static void init() throws Exception {
        configuration = new Configuration(Configuration.VERSION_2_3_0);
        configuration.setLocale(Locale.CHINA);
        configuration.setDefaultEncoding("utf-8");
        configuration.setEncoding(Locale.CHINA, "utf-8");
        configuration.setDirectoryForTemplateLoading(
                new File(FreemarkerUtil.class.getResource("/template").toURI())
        );
    }

    public static void write(String templateName, Map<String, Object> var, String targetFile) throws Exception {
        if (null == configuration) {
            init();
        }
        System.out.println("var.get(\"pkUp\") = " + var.get("pkUp"));
        Template template = configuration.getTemplate(templateName);

        try (FileWriter out = new FileWriter(targetFile)) {
            template.process(var, out);
            out.flush();
        }

    }

}