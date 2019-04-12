package com.github.zhgxun.learn.email;

import com.github.zhgxun.learn.common.util.EmailUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailTest {

    @Autowired
    private EmailUtil emailUtil;

    @Autowired
    private TemplateEngine engine;

    @Test
    public void send() {
        Context context = new Context();
        Map<String, Object> params = new HashMap<>();
        params.put("name", "张三");
        params.put("age", "18");
        params.put("height", "");
        context.setVariables(params);
        String content = engine.process("test", context);
        emailUtil.send("这是一封用 Spring Boot 2 发出的测试邮件", new String[]{"978771018@qq.com"}, null, content);
    }
}
