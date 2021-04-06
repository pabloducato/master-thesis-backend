package pl.edu.prz.master.thesis.backend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailComponent {
    private final JavaMailSender emailSender;

    @Value("${webportal.link}")
    private String webPortalLink;

    public EmailComponent(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public SimpleMailMessage templateNewPasswordMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "This is your new password for Erasmus Assistant:\n%s\n");
        return message;
    }

    public SimpleMailMessage templateResetTokenMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "To reset your password please click the link below:\n%s\n");
        return message;
    }

    public SimpleMailMessage templateNewUserMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(
                "Welcome to Erasmus Assistant:\nThis is your first password:\n%s\n");
        return message;
    }

    public String generateLinkToReset(Long id, String token) {
        return webPortalLink + "/profiles/" + id + "/reset-password/" + token;
    }

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        new Thread(() -> emailSender.send(message)).start();
    }
}
