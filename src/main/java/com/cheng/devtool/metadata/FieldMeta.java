package com.cheng.devtool.metadata;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月26日
 */
public class FieldMeta {

    private String type;
    private String humpName;
    private String pascalName;

    private String columnName;
    private String title;
    private boolean isPk = false;
    private boolean isNullable = false;
    private boolean isSystemField = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getHumpName() {
        return humpName;
    }

    public void setHumpName(String humpName) {
        this.pascalName = humpName.substring(0,1).toUpperCase()+humpName.substring(1);
        this.humpName = humpName;
    }

    public String getPascalName() {
        return pascalName;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        String lowerCase = columnName.toLowerCase();
        if(lowerCase.equals("create_time")||lowerCase.equals("update_time")  ){
            this.setSystemField(true);
        }

        this.columnName = columnName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isPk() {
        return isPk;
    }

    public void setPk(boolean pk) {
        isPk = pk;
    }

    public boolean isNullable() {
        return isNullable;
    }

    public void setNullable(boolean nullable) {
        isNullable = nullable;
    }

    public boolean isSystemField() {
        return isSystemField;
    }

    public void setSystemField(boolean systemField) {
        isSystemField = systemField;
    }

    @Override
    public String toString() {
        return columnName.replaceAll("_","").toLowerCase();
    }
}
