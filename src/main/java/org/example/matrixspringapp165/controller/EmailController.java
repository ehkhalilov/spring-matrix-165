package org.example.matrixspringapp165.controller;

import org.example.matrixspringapp165.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/email")
public class EmailController {

    private final EmailService emailService;

    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send-simple")
    public String sendSimple(
            @RequestParam String to,
            @RequestParam String subject,
            @RequestParam String body) {

        emailService.sendSimpleMail(to, subject, body);
        return "✅ Simple email sent to " + to;
    }

    @GetMapping("/api/send-qr")
    public String sendQr(@RequestParam String to) {
        try {
            emailService.sendQrEmail(to);
            return "✅ Email with QR code sent to " + to;
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Failed to send: " + e.getMessage();
        }
    }
}
