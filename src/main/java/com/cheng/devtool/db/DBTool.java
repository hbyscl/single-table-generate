package com.cheng.devtool.db;

import com.cheng.devtool.metadata.EntityMeta;
import com.cheng.devtool.metadata.FieldMeta;
import com.cheng.devtool.util.Config;
import com.cheng.devtool.util.PropertiesUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author li.cheng
 * @version 1.0.0 2017年04月26日
 */
public class DBTool {

    private List<EntityMeta> data = null;

    private static DBTool instance;

    private DBTool(){

    }

    public static DBTool getInstance() {
        synchronized (DBTool.class){
            if(null == instance){
                synchronized (DBTool.class){
                    instance = new DBTool();
                }
            }
        }
        return instance;
    }

    public List<EntityMeta> getData() {
        if(null != data){
            return data;
        }
        System.out.println("DBTool.getData.run");
        Connection con = null;
        ResultSet rs = null;
        List<EntityMeta> entityMetaList = null;
        try {
            con = getConnection();
            DatabaseMetaData metaData = con.getMetaData();
            entityMetaList = new ArrayList<>();
            String catalog = con.getCatalog();
            rs = metaData.getTables(catalog, "%", "%", new String[]{"TABLE"});
            List<String> settingTables = Config.getTables();
            while (rs.next()) {
                EntityMeta entityMeta = new EntityMeta();
                String tableName = rs.getString("TABLE_NAME");
                if(!settingTables.contains(tableName)){
                    continue;
                }
                entityMeta.setTableName(tableName);
                entityMeta.setTitle(rs.getString("REMARKS"));
                ResultSet colRs = null;
                try {
                    colRs = metaData.getColumns(catalog, "%", tableName, "%");
                    while (colRs.next()) {
                        FieldMeta fieldMeta = new FieldMeta();
                        String columnName = colRs.getString("COLUMN_NAME");
                        fieldMeta.setColumnName(columnName);
                        String isNullable = colRs.getString("IS_NULLABLE");
                        fieldMeta.setNullable("NO".equals(isNullable));
                        String title = colRs.getString("REMARKS");
                        fieldMeta.setTitle(title);
                        entityMeta.addField(fieldMeta);
                    }
                    entityMetaList.add(entityMeta);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    close(colRs);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(rs);
            close(con);
        }
        System.out.println("DBTool.getData.done");
        return entityMetaList;
    }

    private Connection getConnection() throws Exception {
        Connection connection;
        Class.forName(PropertiesUtil.getProperties("jdbc.driver"));
        Properties props = new Properties();
        props.setProperty("user", PropertiesUtil.getProperties("jdbc.user"));
        props.setProperty("password", PropertiesUtil.getProperties("jdbc.password"));
        props.setProperty("remarks", "true"); //设置可以获取remarks信息
        props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息
        connection = DriverManager.getConnection(
                PropertiesUtil.getProperties("jdbc.url"),
                props

        );
        return connection;
    }

    //关闭连接
    private void close(Object o) {
        if (o == null) {
            return;
        }
        if (o instanceof ResultSet) {
            try {
                ((ResultSet) o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof Statement) {
            try {
                ((Statement) o).close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if (o instanceof Connection) {
            Connection c = (Connection) o;
            try {
                if (!c.isClosed()) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
