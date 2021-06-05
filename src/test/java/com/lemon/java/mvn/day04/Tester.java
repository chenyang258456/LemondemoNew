package com.lemon.java.mvn.day04;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Tester {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //获取类的字节码方式1
        Class clazz = Student.class;
        //获取类的字节码方式2
        Class<Student> aClass = (Class<Student>) Class.forName("com.lemon.java.mvn.day04.Student");
         //通过字节码调用newInstance方法创建对象，底层其实调用的是字节码对应类中的默认构造函数
        Student student = (Student) clazz.newInstance();
         //通过反射获取我们要调用的方法
        Method method = clazz.getMethod("setName", String.class);
        //通过反射完成方法调用
        method.invoke(student,"张三");
        //通过getName取出来数据
        Method getNameMethod = clazz.getMethod("getName");
        System.out.println(getNameMethod.invoke(student));


//        Method[] methods = clazz.getMethods();
//        for (Method method:methods
//             ) {
//            System.out.println(method);
//
//        }



    }
}
