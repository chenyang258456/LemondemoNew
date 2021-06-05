package com.lemon.java.mvn.day02;

import java.io.*;
import java.util.Properties;

public class ThrowDemo {
    public static void main(String[] args) {
        String filepath = "src/main/resources/log4j.properties";
        File file = new File(filepath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());;;
        }
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (FileNotFoundException f){
            f.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO异常");;
        }catch (NullPointerException e){
            System.out.println("");;
        }finally {
            if(inputStream!=null){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {
                System.out.println("inputStream为空");
            }
        }
        System.out.println(properties.get("log4j.appender.stdout"));

    }
}
