package com.github.zhgxun.learn.notes.config;

import com.github.zhgxun.learn.notes.Person;
import com.github.zhgxun.learn.notes.UserDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

// 普通注解的bean名称为类名称首字母小写, 故不允许重复, 如果存在多个类则需要使用别名
@Configuration
// 导入的类可以加载类全路径, 故不关心导入的类名本身是否重复
@Import({UserDao.class, com.github.zhgxun.learn.notes.dao.UserDao.class})
public class BeanConfig {

    @Bean
    public Person person() {
        return new Person("张三", 18, "高年级学生");
    }
}
