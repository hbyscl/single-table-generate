package com.cheng.devtool.metadata;

import com.cheng.devtool.db.DBTool;
import com.cheng.devtool.util.Config;
import com.cheng.devtool.util.JavaFile;

import java.util.List;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月26日
 */
public class EntityAnalyzer {
    public static List<EntityMeta> build() {
        List<EntityMeta> metas = DBTool.getInstance().getData();
        System.out.println("EntityAnalyzer.build.run");
        String entityPath = Config.getEntityPath();
        for (EntityMeta meta : metas) {
            meta.setPackageName(Config.getPackage());
            String flatName = meta.getFlatName();
            meta.setPascalName(JavaFile.getClassName(entityPath, flatName));

            List<FieldMeta> fieldList = meta.getFieldList();

            List<String> javaFieldList =
                    JavaFile.startWith(entityPath, flatName, "private ");
            String javaPkField = JavaFile.contains(Config.getDaoPath(),flatName+"mapper","selectByPrimaryKey").get(0);

            for (String javaField : javaFieldList) {
                // Integer userId
                String[] s = javaField.split(" ");
                for (FieldMeta fieldMeta : fieldList) {
                    if(s[1].toLowerCase().equals(fieldMeta.toString())){
                        fieldMeta.setHumpName(s[1]);
                        fieldMeta.setType(s[0]);
                        if(javaPkField.contains(s[1])){
                            fieldMeta.setPk(true);
                            meta.setPk(fieldMeta);
                        }
                    }
                }
            }
        }
        System.out.println("EntityAnalyzer.build.done");
        return metas;
    }

}
