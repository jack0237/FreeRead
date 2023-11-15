package com.sklfgroup.auxillium.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EmailServiceImp {

    private JavaMailSender mailSender;

    @Autowired
    public EmailServiceImp(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendConfirmationEmail(String toEmail, String confirmationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("Confirmation d'inscription");
        message.setText("Cliquez sur le lien suivant pour confirmer votre inscription : " + confirmationLink);
        mailSender.send(message);
    }

    public String generateConfirmationLink() {
        // Générez un token unique pour le lien de confirmation
        String token = UUID.randomUUID().toString();

        // Construisez le lien de confirmation avec le token et l'URL de votre application
        String confirmationLink = "https://sklfgroup.com/confirmation?token=" + token;

        return confirmationLink;
    }
}
