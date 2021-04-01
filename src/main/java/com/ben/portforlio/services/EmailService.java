package com.ben.portforlio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

/**
 * @author bkariuki
 */
@Component
public class EmailService {
    @Autowired
    protected EmailSenderService emailSenderService;

    public void sendEmail(String emailAddress,String title, String message){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setText(message);
        mailMessage.setSubject(title);
        mailMessage.setTo(emailAddress);
        System.out.println("SENDING EMAIL *****************************");
        emailSenderService.sendEmail(mailMessage);
    }
}
