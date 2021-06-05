package com.lemon.java.mvn.day03;

import java.io.*;

public class FileCopy {
    public static void main(String[] args) throws IOException {
        String fromPath = "src/test/target/124a8b59e0b1b2bc8e4880e652673010(1).jpg";
        String toPath = "src/test/target/124a8b59e0b1b2bc8e4880e652673010(2).jpg";
        InputStream inputStream = new FileInputStream(new File(fromPath));
        OutputStream outputStream = new FileOutputStream(new File(toPath));
        int size = 0;
        while ((size=inputStream.read()) !=-1){
                outputStream.write(size);
        }
        if (inputStream !=null){
            inputStream.close();
        }
        if (outputStream!=null){
            outputStream.close();
        }
        System.out.println("finish");
    }
    }

