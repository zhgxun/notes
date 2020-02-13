package com.github.zhgxun.learn.notes.spring.beans;

import java.beans.PropertyEditorSupport;

/**
 * 将特定格式的字符串转型为特定的对象类型
 * <p>
 * userId,name,age,amount
 */
public class String2PersonPropertyEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        String[] tokens = text.split("\\,");
        setValue(new Person(Long.valueOf(tokens[0]), tokens[1], Integer.valueOf(tokens[2]), Double.valueOf(tokens[3])));
    }
}
