package com.lemon.java.mvn.day04;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class XmlDemo {
    public static void main(String[] args) throws Exception {
        String path = "src/main/resources/Tester.xml";
        List<Student> students = parseXml(path);
        for (Student student : students
        ) {
            System.out.println(student.toString());
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
            Student student = clazz.newInstance();
            for (Element element : elements) {
                String elementName = element.getName();
//                System.out.println(elementName);
                elementName = "set" + elementName;
                Method method = clazz.getMethod(elementName, String.class);
                method.invoke(student, element.getText());
            }
            students.add(student);

            //System.out.println("name="+name+",age="+age+",gender="+gender);

        }

        return students;

    }
}
