package com.lemon.java.mvn.day04;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectDemo {
    public static void main(String[] args) throws Exception {
        String path = "src/main/resources/Tester.xml";
        List<Student> students = parseXml(path);
        for (Student student:students
             ) {
            System.out.println(student);
        }


    }

    public static List<Student> parseXml(String path) throws Exception {
        //创建解析器SaxReader对象
        SAXReader saxReader = new SAXReader();
        //获取document对象
        Document document = saxReader.read(new File(path));
        //获取根元素
        Element rootElement = document.getRootElement();
        //获取根元素下的子元素
        List<Element> studentElements = rootElement.elements("student");
        List<Student> students = new ArrayList<Student>();
        Class<Student> clazz = Student.class;

        for (Element studentElement : studentElements) {
             List<Element> elements = studentElement.elements();
             //多次获取学生对象
             Student student = clazz.newInstance();
           for ( Element element:elements ) {
               //获取子元素的标签名
               String studentElementName = element.getName();
               //根据获取到的标签名拼接为Student内的方法名
               studentElementName= "set"+studentElementName;
               //根据方法名获取方法，传入参数类型：String.class获取方法对象
               Method method = clazz.getMethod(studentElementName);

               //执行方法，将标签内容作为参数传递给该方法
               method.invoke(student,element.getText());
           }
           //将不同的学生对象加入到列表中
            students.add(student);
        }

        return students;
    }

}
