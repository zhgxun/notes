package com.github.zhgxun.learn.common.bean;

import lombok.Data;

@Data
public class TestBean {
    private int id;
    private String name;
    private int age;

    public TestBean() {

    }

    public TestBean(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
