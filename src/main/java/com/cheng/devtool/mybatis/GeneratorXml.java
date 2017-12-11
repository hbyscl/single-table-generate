package com.cheng.devtool.mybatis;

import com.cheng.devtool.db.DBTool;
import com.cheng.devtool.metadata.EntityAnalyzer;
import com.cheng.devtool.metadata.EntityMeta;
import com.cheng.devtool.util.PropertiesUtil;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.dom4j.tree.DefaultAttribute;
import org.dom4j.tree.DefaultElement;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月24日
 * @since soter 1.0.0
 */
public class GeneratorXml {

    private static Document initDocument() throws Exception {
        SAXReader reader = new SAXReader();
        URI uri = GeneratorXml.class.getResource("/mybatisGenerator.xml").toURI();
        File file = new File(uri);
        Document document = reader.read(file);
        Element root = document.getRootElement();
        List<Element> context = root.elements("context");
        context.forEach(c -> {
            List<Element> table = c.elements("table");
            table.forEach(t -> c.remove(t));
        });
        return document;
    }

    private static Document addTableEl(Document document) throws Exception {
        Element root = document.getRootElement();
        List<Element> context = root.elements("context");
        Element cont = context.get(0);
        String jdbcTables = PropertiesUtil.getProperties("tables");
        String[] tabArr = jdbcTables.split(",");

        Map<String,EntityMeta> meta = getMeta();
        for (String table : tabArr) {
            Element element = new DefaultElement("table");
            element.add(new DefaultAttribute("tableName", table));

            EntityMeta entity = meta.get(table);
            if (null != entity && null != entity.getPk()) {
                Element generatedKey = new DefaultElement("generatedKey");
                generatedKey.add(new DefaultAttribute("column", entity.getPk().getColumnName().toLowerCase()));
                generatedKey.add(new DefaultAttribute("sqlStatement", "MySql"));
                generatedKey.add(new DefaultAttribute("identity", "true"));

                element.add(generatedKey);
            }


            cont.add(element);
        }
        return document;
    }

    private static Map<String,EntityMeta> getMeta(){
        List<EntityMeta> metas = EntityAnalyzer.build();
        Map<String,EntityMeta> ret = new HashMap<>();
        metas.forEach(e-> ret.put(e.getTableName(),e));
        return ret;
    }

    private static void writeDocument(Document document) throws Exception {

        OutputStream outputStream = new FileOutputStream(new File(
                GeneratorXml.class.getResource("/mybatisGenerator.xml").toURI()
//                System.getProperty("user.dir") + "/src/main/resources/mybatisGenerator.xml"
        ));

        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        //1.创建写出对象
        XMLWriter writer = new XMLWriter(outputStream, format);

        //2.写出Document对象
        writer.write(document);

        //3.关闭流
        writer.close();
    }

    public static void run() throws Exception {
        System.out.println("GeneratorXml.run");
        Document document = initDocument();
        addTableEl(document);
        writeDocument(document);
        System.out.println("GeneratorXml.done");
    }

}
