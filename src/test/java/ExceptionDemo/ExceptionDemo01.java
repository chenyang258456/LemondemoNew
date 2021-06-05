package ExceptionDemo;

import org.apache.log4j.Logger;

import java.io.*;
import java.util.Properties;

public class ExceptionDemo01 {
    public static Logger logger = Logger.getLogger(ExceptionDemo01.class);

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        File file = new File("src/main/resources/log4j.properties");
        InputStream inputStream = new FileInputStream(file);
        properties.load(inputStream);
        Object value = properties.get("log4j.appender.stdout.layout");
        System.out.println(value);
        System.out.println(10/0);

    }
}
