package com.example.bdsmailservice.Service;

import com.example.bdsmailservice.Receiver.MailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class MailService {
    private static final String CONTENT_TYPE_TEXT_HTML = "text/html;charset=\"utf-8\"";

//    @Value("${config.mail.host}")
    private String host = "smtp.gmail.com";
//    @Value("${config.mail.port}")
    private String port = "587";
//    @Value("${config.mail.username}")
    private String email = "vntrieu9004@gmail.com";
//    @Value("${config.mail.password}")
    private String password = "Vntrieu9004())$";

    @Autowired
    ThymeleafService thymeleafService;

    public void sendMail(MailData mailData) {
        Properties props = new Properties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(email, password);
                    }
                });
        Message message = new MimeMessage(session);
        try {
            message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress(mailData.getEmail())});

            message.setFrom(new InternetAddress(email));
            message.setSubject("Gợi ý sản phẩm từ BDS");
            message.setContent(thymeleafService.getContent(
                    mailData.getNameUser(),
                    mailData.getListLink()
                    ), CONTENT_TYPE_TEXT_HTML);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
