package com.github.zhgxun.learn.notes.spring.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class BeanSet {

    public static void setBean(Student student, String desc) throws IntrospectionException {
        // 注册一个类的属性编辑器
        PropertyEditorManager.registerEditor(Person.class, String2PersonPropertyEditor.class);

        // 获取目标对象的 beanInfo 信息
        BeanInfo beanInfo = Introspector.getBeanInfo(Student.class, Object.class);

        Arrays.stream(beanInfo.getPropertyDescriptors()).forEach(p -> {
            Class<?> propertyType = p.getPropertyType();
            // 找到要处理的属性名称
            if (propertyType.equals(Person.class)) {
                // 是否存在该属性编辑器
                PropertyEditor editor = PropertyEditorManager.findEditor(propertyType);
                if (editor != null) {
                    // 处理属性
                    editor.setAsText(desc);
                    try {
                        // 将处理后的属性用java内省的方法直接进行对象赋值ø
                        p.getWriteMethod().invoke(student, editor.getValue());
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
