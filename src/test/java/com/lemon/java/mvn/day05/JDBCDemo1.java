package com.lemon.java.mvn.day05;

import java.io.*;
import java.sql.*;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class JDBCDemo1 {
    public static void main(String[] args) throws SQLException, IOException {
        String sql1 = "SELECT id,type,Pwd,MobilePhone FROM `member` where id =?";
        String sql2 = "SELECT id,memberid,amount FROM `invest` where id =?";
        Object[] values1 = {"23512"};
        Object[] values2 = {1};
        Map<String,Object> columnLabelAndValues1 = JDBCUtil.query(sql1, values1);
        System.out.println("开始遍历map");
//        Set<String> columnLabels = columnLabelAndValues1.keySet();
//        for ( String columnLabel:columnLabels  ) {
//            System.out.println("columnLabel="+columnLabel+",columnValue="+columnLabelAndValues1.get(columnLabel));
//        }
        for (Map.Entry<String,Object> entry:columnLabelAndValues1.entrySet() ) {
            System.out.println("columnLabel="+entry.getKey()+",columnValue="+entry.getValue());
        }


    }

    public static void query() throws IOException {
        String sql = "SELECT * FROM `member` where id =? and TYPE =?";
        Connection connection = null;
        Properties properties = new Properties();
        InputStream inputStream = new FileInputStream(new File("src/main/resources/jdbc.properties"));
        properties.load(inputStream);
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.username");
        String passWord = properties.getProperty("jdbc.password");
        try {
            connection = DriverManager.getConnection(url, user, passWord);
            //获取PrepareStatement,此类型的对象提供操作数据库的方法
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //设置条件字段的值
            preparedStatement.setObject(1, "23512");
            //调用查询方法，执行查询，返回ResultSet
            ResultSet resultSet = preparedStatement.executeQuery();
            //从结果集中查询数据
            while (resultSet.next()) {
                //根据列名取出数据
                String regNamevalue = resultSet.getString("regname");
                System.out.println(regNamevalue);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
