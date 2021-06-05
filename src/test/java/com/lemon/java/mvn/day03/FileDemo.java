package com.lemon.java.mvn.day03;

import java.io.File;
import java.io.IOException;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        //判断目录是否存在
        String path1 = "E:\\aa\\bb\\cc.txt";
        File dir =new File( "E:\\aa\\bb");
        File file = new File(path1);
        System.out.println(file.exists());
        file.createNewFile();
    }
}
