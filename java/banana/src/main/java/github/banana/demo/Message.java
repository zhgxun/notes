package github.banana.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>
@Component
public class Message {
    private MessageService service;

    /**
     * 框架自动注入依赖注入
     * 
     * @param service
     */
    @Autowired
    public Message(MessageService service) {
        this.service = service;
    }

    /**
     * 打印信息
     * 
     * @return
     */
    public void show() {
        System.out.println(this.service.getMessage());
    }
}
