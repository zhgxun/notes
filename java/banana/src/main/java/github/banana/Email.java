package github.banana;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 * 邮箱公共信息
 *
 * @author zhgxun
 */
public class Email {
    // 邮箱服务地址
    private String host = "smtp.sina.com";
    // 邮箱用户名
    private String username = "zhgxun1989@sina.com";
    // 邮箱密码
    private String password = "******";
    // 是否开启调试模式
    private boolean debug = true;
    // 邮箱服务端口
    private String port = "465";

    public Email() {

    }

    /**
     * 可复写默认配置
     *
     * @param host     邮件主机
     * @param username 用户名
     * @param password 密码
     * @param debug    调试模式
     * @param port     端口
     */
    public Email(String host, String username, String password, boolean debug, String port) {
        this.host = host;
        this.username = username;
        this.password = password;
        this.debug = debug;
        this.port = port;
    }

    /**
     * 使用SSL方式认证
     *
     * @return javax.mail.Session
     */
    public Session SSLSession() {
        Properties properties = new Properties();
        // 主机
        properties.put("mail.smtp.host", this.host);
        // 端口
        properties.put("mail.smtp.port", this.port);
        // 启用用户登陆认证
        properties.put("mail.smtp.auth", "true");
        // 启用SSL
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.socketFactory.port", this.port);
        // 用户认证
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                Email email = new Email();
                return new PasswordAuthentication(email.username, email.password);
            }
        });
        // 显示调试模式
        session.setDebug(this.debug);
        return session;
    }

    /**
     * 发送文本
     *
     * @param session javax.mail.Session
     * @param from    发信人
     * @param to      收信人
     * @param subject 邮件主题
     * @param body    邮件内容
     * @return Message
     * @throws MessagingException Message异常
     */
    public Message text(Session session, String from, String to, String subject, String body)
            throws MessagingException {
        // 使用MimeMessage来发送邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        // 设置发件人
        mimeMessage.setFrom(new InternetAddress(from));
        // 设置收件人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 设置主题
        mimeMessage.setSubject(subject, "UTF-8");
        // 设置内容
        mimeMessage.setText(body, "UTF-8");
        return mimeMessage;
    }

    /**
     * 发送html格式邮件
     *
     * @param session
     * @param from
     * @param to
     * @param subject
     * @param body
     * @return
     * @throws MessagingException
     */
    public Message html(Session session, String from, String to, String subject, String body)
            throws MessagingException {
        // 使用MimeMessage来发送邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        // 设置发件人
        mimeMessage.setFrom(new InternetAddress(from));
        // 设置收件人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 设置主题
        mimeMessage.setSubject(subject, "UTF-8");
        // 设置内容
        mimeMessage.setText(body, "UTF-8", "html");
        return mimeMessage;
    }

    /**
     * 发送图片附件
     *
     * @param session
     * @param from
     * @param to
     * @param subject
     * @param body
     * @param fileName
     * @param inputStream
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public Message attachment(Session session, String from, String to, String subject, String body, String fileName,
                              InputStream inputStream) throws MessagingException, IOException {
        // 使用MimeMessage来发送邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        // 设置发件人
        mimeMessage.setFrom(new InternetAddress(from));
        // 设置收件人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 设置主题
        mimeMessage.setSubject(subject, "UTF-8");
        // 设置邮件主体body
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(body, "text/html;charset=utf-8");
        // 发送附件需要使用multi
        Multipart multipart = new MimeMultipart();
        // 将主体添加到multi中
        multipart.addBodyPart(bodyPart);
        // 添加一张图片附件
        BodyPart imagePart = new MimeBodyPart();
        imagePart.setFileName(fileName);
        imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(inputStream, "application/octet-stream")));
        multipart.addBodyPart(imagePart);
        mimeMessage.setContent(multipart);
        return mimeMessage;
    }

    /**
     * 发送一张内嵌的图片
     *
     * @param session
     * @param from
     * @param to
     * @param subject
     * @param body
     * @param fileName
     * @param inputStream
     * @return
     * @throws MessagingException
     * @throws IOException
     */
    public Message image(Session session, String from, String to, String subject, String body, String fileName,
                         InputStream inputStream) throws MessagingException, IOException {
        // 使用MimeMessage来发送邮件
        MimeMessage mimeMessage = new MimeMessage(session);
        // 设置发件人
        mimeMessage.setFrom(new InternetAddress(from));
        // 设置收件人
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        // 设置主题
        mimeMessage.setSubject(subject, "UTF-8");
        // 设置邮件主体body
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(body, "text/html;charset=utf-8");
        // 发送附件需要使用multi
        Multipart multipart = new MimeMultipart();
        // 将主体添加到multi中
        multipart.addBodyPart(bodyPart);
        // 添加一张图片附件
        BodyPart imagePart = new MimeBodyPart();
        imagePart.setFileName(fileName);
        imagePart.setDataHandler(new DataHandler(new ByteArrayDataSource(inputStream, "application/octet-stream")));
        // 与HTML的<img src="cid:img01">关联:
        imagePart.setHeader("Content-ID", "<img01>");
        multipart.addBodyPart(imagePart);
        mimeMessage.setContent(multipart);
        return mimeMessage;
    }

}
