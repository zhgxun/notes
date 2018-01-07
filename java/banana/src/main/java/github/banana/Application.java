package github.banana;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {
    public static void main(String[] args) {
        @SuppressWarnings("resource")
        ApplicationContext context = new AnnotationConfigApplicationContext(Application.class);
        Message printer = context.getBean(Message.class);
        printer.show();
    }

    @Bean
    MessageService mockMessageService() {
        return new MessageService() {
            
            @Override
            public String getMessgee() {
                return "你好，世界。";
            }
        };
    }
}
