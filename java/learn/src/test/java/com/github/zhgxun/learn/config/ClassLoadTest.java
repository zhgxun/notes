package com.github.zhgxun.learn.config;

import com.google.gson.Gson;
import org.springframework.boot.autoconfigure.web.ServerProperties;

import java.net.http.HttpClient;
import java.util.ArrayList;

/**
 * 类加载过程
 */
public class ClassLoadTest {

    public static void main(String[] args) throws Exception {
        // 普通类加载器, 对于jdk8来说
        Class<?> person = Class.forName("com.github.zhgxun.learn.notes.Person");
        // 第一级别为 应用类加载器 jdk.internal.loader.ClassLoaders$AppClassLoader@2c13da15
        System.out.println(person.getClassLoader());
        // 第二级别为 扩展类加载器
        System.out.println(person.getClassLoader().getParent());
        // 第三级别为 启动类加载器, 启动类加载器不显示, 因为是C类语言实现的
        System.out.println(person.getClassLoader().getParent().getParent());

        // 对于jdk11来说只有平台类加载器了
        // jdk.internal.loader.ClassLoaders$PlatformClassLoader@402f32ff
        System.out.println(ClassLoadTest.class.getClassLoader());
        System.out.println(String.class.getClassLoader());
        System.out.println(ArrayList.class.getClassLoader());
        System.out.println(HttpClient.class.getClassLoader());
        System.out.println(ServerProperties.Tomcat.class.getClassLoader());
        System.out.println(Gson.class.getClassLoader());
    }
}
