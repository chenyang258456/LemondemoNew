package com.lemon.java.mvn.day04;

import java.io.*;
import java.util.Properties;

public class LoadPropertiesDemo {

    public static void main(String[] args) throws IOException {
        String filePath = "src/main/resources/log4j.properties";
        loadProperties(filePath);
    }

    public static void loadProperties(String filePath) throws IOException {
        Properties properties = new Properties();

        InputStream inputStream = new FileInputStream(new File(filePath));
        properties.load(inputStream);
        System.out.println(properties.get("log4j.appender.stdout.layout.ConversionPattern"));
    }
}
