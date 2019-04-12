package com.github.zhgxun.learn.common.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Component
public class EmailUtil {

    @Autowired
    private JavaMailSender sender;

    private String from = "zhgxun1989@163.com";

    public void send(String subject, String[] to, String[] c, String content) {
        MimeMessage message = sender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            // 邮件主题
            helper.setSubject(subject);
            // 发送者
            helper.setFrom(from);
            // 接收者
            helper.setTo(to);
            // 抄送者
            if (c != null) {
                helper.setCc(c);
            }
            // 邮件内容
            helper.setText(content, true);
            sender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
