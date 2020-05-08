package com.text.rexiufu.fanshe;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取Class对象的三种方式
 * 在运行期间，一个类，只有一个Class对象产生
 * <p>
 * 三种方式常用第三种
 * 第一种对象都有了还要反射干什么。
 * 第二种需要导入类的包，依赖太强，不导包就抛编译错误。
 * 一般都第三种，一个字符串可以传入也可写在配置文件中等多种方法。
 * <p>
 * 1 Object ——> getClass();
 * 2 任何数据类型（包括基本数据类型）都有一个“静态”的class属性
 * 3 通过Class类的静态方法：forName（String  className）(常用)
 */
public class Fanshe {
    public static void main(String[] args) {

//        LinkedHashMap<String, String> value = new LinkedHashMap<>();
//        value.put("888", "adsas");
//        value.put("88899", "adsas");
//        for (String key : value.keySet()) {
//            System.out.println(key);
//        }

        try{
            throw new RuntimeException("try");
        }finally{
            throw new RuntimeException("finally");
        }
















//        System.out.println("222".split("\\,").length);
//        System.out.println(",".split("\\,").length);
//        System.out.println(" ,".split("\\,").length);
//        System.out.println(" , ".split("\\,").length);
//        System.out.println("1,".split("\\,").length);
//        System.out.println("1,1".split("\\,").length);
//        System.out.println(-630/600);
//        System.out.println(-630/600*100);
//        System.out.println(-630/600*100/100f);
//        System.out.println(30/600*100);

        //第一种方式获取Class对象
//        Student stu1 = new Student();//这一new 产生一个Student对象，一个Class对象。
//        Class stuClass = stu1.getClass();//获取Class对象
//        System.out.println(stuClass.getName());
//
//        //第二种方式获取Class对象
//        Class stuClass2 = Student.class;
//        System.out.println(stuClass == stuClass2);//判断第一种方式获取的Class对象和第二种方式获取的是否是同一个
//
//        //第三种方式获取Class对象
//        try {
//            Class stuClass3 = Class.forName("com.text.rexiufu.fanshe.Student");//注意此字符串必须是真实路径，就是带包名的类路径，包名.类名
//            System.out.println(stuClass3 == stuClass2);//判断三种方式是否获取的是同一个Class对象
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

    }
}