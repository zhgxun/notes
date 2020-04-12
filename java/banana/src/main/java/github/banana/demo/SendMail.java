package github.banana.demo;

import java.io.IOException;
import java.io.InputStream;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;

/**
 * 发送邮件
 *
 * @author zhgxun
 */
public class SendMail {
    public static void main(String[] args) {
        text();
//        html();
//        try {
//            attachment();
//            image();
//        } catch (IOException | MessagingException e) {
//            e.printStackTrace();
//        }
    }

    /**
     * 发送文本邮件
     */
    private static void text() {
        Email email = new Email();
        Session session = email.SSLSession();
        try {
            Message message = email.text(session, "z@oef.org.cn", "zhangguangxun@xiaomi.com", "千与千寻",
                    "天空是连着的，如果我们也能各自发光的话，无论距离有多远，都能看到彼此努力的身影。");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送html格式邮件
     */
    private static void html() {
        Email email = new Email();
        Session session = email.SSLSession();
        Message message;
        try {
            message = email.text(session, "zhgxun1989@sina.com", "zhgxun1989@163.com", "风晴雪独白",
                    "<p>曾经有人告诉过我，对生死之事毫无执念的人，只是因为还没有经历过真正绝望的别离。仿佛诅咒一般……</p>");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送图片附件
     *
     * @throws IOException
     *             IO异常
     * @throws MessagingException
     *             Message异常
     */
    private static void attachment() throws IOException, MessagingException {
        Email email = new Email();
        Session session = email.SSLSession();
        try (InputStream inputStream = email.getClass().getResourceAsStream("/LinuxStory.jpeg")) {
            Message message = email.attachment(session, "zhgxun1989@sina.com", "zhgxun1989@163.com", "这是一次北京的小聚会",
                    "还记得15年华华去北京的时候，我们 LinuxStory 群里的几个“死党”聚会并没有正式的分享。我们晚上撸串，撸了串去中关村创业大街晃了一圈，谈天说地。10月北京的深夜还是透心凉的，但是我们几个好像怎么都聊不完，烤串吃了 n 久+逛了创业大街后说应该回家了，但是走到一个十字路口还是不自觉地停下来又聊了很久很久，路上的行人越来越少……那时候的场景还深深印在我脑海！",
                    "LinuxStory.jpeg", inputStream);
            Transport.send(message);
        }
    }

    /**
     * 发送内嵌图片
     *
     * @throws IOException
     *             IO异常
     * @throws MessagingException
     *             Message异常
     */
    private static void image() throws IOException, MessagingException {
        Email email = new Email();
        Session session = email.SSLSession();
        try (InputStream inputStream = email.getClass().getResourceAsStream("/LinuxStory.jpeg")) {
            Message message = email.attachment(session, "zhgxun1989@sina.com", "zhgxun1989@163.com", "这是一次北京的小聚会",
                    "还记得15年华华去北京的时候，我们 LinuxStory 群里的几个“死党”聚会并没有正式的分享。我们晚上撸串，撸了串去中关村创业大街晃了一圈，谈天说地。10月北京的深夜还是透心凉的，但是我们几个好像怎么都聊不完，烤串吃了 n 久+逛了创业大街后说应该回家了，但是走到一个十字路口还是不自觉地停下来又聊了很久很久，路上的行人越来越少……那时候的场景还深深印在我脑海！<img src=\"cid:img01\">LinuxStory 的线下聚会就是这样，不管在哪里，你都会觉得暖心，你都会感受到真诚，你都会爱上我们的气氛。",
                    "LinuxStory.jpeg", inputStream);
            Transport.send(message);
        }
    }
}
