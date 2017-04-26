package com.cheng.devtool.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月26日
 */
public class EntityMeta {
    private String packageName;
    private String pascalName;
    private String humpName;
    private String flatName;

    private String tableName;
    private String title;

    private FieldMeta pk;
    private List<FieldMeta> fieldList;

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getPascalName() {
        return pascalName;
    }

    public void setPascalName(String pascalName) {
        this.pascalName = pascalName;
        this.humpName = pascalName.substring(0,1).toLowerCase()+ pascalName.substring(1);
    }

    public String getHumpName() {
        return humpName;
    }


    public String getFlatName() {
        return flatName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
        this.flatName = tableName.replaceAll("_","").toLowerCase();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public FieldMeta getPk() {
        return pk;
    }

    public void setPk(FieldMeta pk) {
        this.pk = pk;
    }

    public List<FieldMeta> getFieldList() {
        return fieldList;
    }

    public void addField(FieldMeta fieldMeta){
        if(fieldList == null){
            fieldList = new ArrayList<>();
        }
        fieldList.add(fieldMeta);
    }

    public void setFieldList(List<FieldMeta> fieldList) {
        this.fieldList = fieldList;
    }

    @Override
    public String toString() {
        return "{" +
                "packageName='" + packageName + '\'' +
                ", pascalName='" + pascalName + '\'' +
                ", humpName='" + humpName + '\'' +
                ", flatName='" + flatName + '\'' +
                ", tableName='" + tableName + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
