package com.lemon.java.mvn.day05;
import java.io.*;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {
    public static Properties properties = new Properties();
    static {
        System.out.println("静态代码块解析properties数据");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("src/main/resources/jdbc.properties"));
            properties.load(inputStream);
        } catch (FileNotFoundException e) {
            System.out.println("文件找不到");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("发生了IO异常");
            e.printStackTrace();
        }
    }

    public static Map<String,Object> query(String sql, Object...values) throws IOException {
        Map<String,Object> columnLableAndValues = null;
        try {
            System.out.println("开始解析Properties文件");
            //获取连接
            Connection connection = getConnection();
            //获取PrepareStatement,此类型的对象提供操作数据库的方法
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            //封装条件字段的值
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i+1, values[i]);
            }
            //调用查询方法，执行查询，返回ResultSet
            ResultSet resultSet= preparedStatement.executeQuery();
            //获取查询相关的信息
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            //得到查询字段的数目
            int columnCount=resultSetMetaData.getColumnCount();
            //从结果集中查询数据
            columnLableAndValues = new HashMap<>();
            while (resultSet.next()) {
                //循环取出每个查询字段的数据
                for (int i = 1; i <= columnCount; i++) {
                    //获取列名
                    String columnLable = resultSetMetaData.getColumnLabel(i);
                    //传入列名进行查询
                    String columnvalue = resultSet.getString(columnLable).toString();
                    columnLableAndValues.put(columnLable,columnvalue);
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return columnLableAndValues;
    }
    /*
    * 获取数据库连接connection*/
    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("jdbc.url");
        String user = properties.getProperty("jdbc.username");
        String passWord = properties.getProperty("jdbc.password");
        Connection connection = DriverManager.getConnection(url, user, passWord);
        return connection;
    }


}
