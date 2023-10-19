package com.geekster.Restaurant.App.Service.EmailHandler;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailHandler {

    private static final String UserEmail = "mmrinalgautam1999@gmail.com";

    private static final String UserPassword = "";

    public static boolean sendEmail(String toEmail, String subject, String body){
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.post","587");

        Session session = Session.getInstance(properties, new Authenticator(){

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return super.getPasswordAuthentication();
            }
        });
        Message message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(UserEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("OTP sent successfully to " + toEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
