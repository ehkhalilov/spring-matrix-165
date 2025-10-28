package org.example.matrixspringapp165.service;

import jakarta.mail.internet.MimeMessage;
import org.example.matrixspringapp165.util.QrGeneratorUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendSimpleMail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        mailSender.send(message);
        System.out.println("✅ Simple email sent successfully to " + to);
    }

    public void sendQrEmail(String to) throws Exception {
        byte[] qrBytes = QrGeneratorUtil.generateQr("https://www.snakesushi.az/", 300);

        String htmlBody = """
                <h2>Welcome to Snake Sushi 🍣</h2>
                <p>Scan the QR below to visit our website:</p>
                <p><b>https://www.snakesushi.az/</b></p>
                """;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject("Snake Sushi QR Code 🍱");
        helper.setText(htmlBody, true);

        helper.addAttachment("snakesushi_qr.png", new ByteArrayResource(qrBytes));

        mailSender.send(message);
        System.out.println("✅ Email with QR sent to " + to);
    }
}
