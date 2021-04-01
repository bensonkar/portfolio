package com.ben.portforlio.resources;

import com.ben.portforlio.services.EmailSenderService;
import com.ben.portforlio.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author bkariuki
 */
@RestController
public class Test {
    @Autowired
    EmailSenderService emailService;
    @Autowired
    EmailService service;
    @PostMapping("/test")
    public void test() {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setText("test");
//        mailMessage.setSubject("test");
//        mailMessage.setTo("test@gmail.com");
//        emailService.sendEmail(mailMessage);
        service.sendEmail("test@gmail.com","test","test");
    }
}
