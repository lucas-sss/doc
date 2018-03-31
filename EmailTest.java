package com.example.demo;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * @author liuwei 1215946336@qq.com
 * @version 1.0
 * @date 2018/3/9 0009
 */
public class EmailTest {

    public static void main(String[] args) {


        Properties props = new Properties();
        props.put("mail.smtp.port", "25");
        // 设置邮件服务器主机名
        props.put("mail.smtp.host", "smtp.163.com");
        // 发送邮件协议名称
        props.put("mail.transport.protocol", "smtp");

        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("lyy9645@163.com", "strliu0605");
            }
        });
        session.setDebug(true);


        Message message = new MimeMessage(session);

        try {
            message.setSubject("测试附件");
            message.setFrom(new InternetAddress("lyy9645@163.com"));
            message.setRecipients(Message.RecipientType.TO, new Address[]{new InternetAddress("1215946336@qq.com")});


            MimeMultipart multipart = new MimeMultipart("mixed");//有附件呀定义为mixed

            //设置文本
            MimeBodyPart text = new MimeBodyPart();
            text.setContent("我是一只小小鸟  <a href='http://www.baidu.com'>百度</a>", "text/html;charset=UTF-8");

            //附件
            MimeBodyPart attch = new MimeBodyPart();
            DataSource ds = new FileDataSource("F:\\阿里云服务器.txt");
            DataHandler dh = new DataHandler(ds);
            attch.setDataHandler(dh);
            try {
                attch.setFileName(MimeUtility.encodeText("阿里云.txt"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }


            multipart.addBodyPart(attch);
            multipart.addBodyPart(text);

            message.setContent(multipart);


            Transport.send(message, message.getAllRecipients());

        } catch (MessagingException e) {
            e.printStackTrace();
        }


    }


}
