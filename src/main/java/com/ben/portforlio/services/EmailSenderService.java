package com.ben.portforlio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@Service("emailSenderService")
public class EmailSenderService {

    private JavaMailSender javaMailSender;
    @Autowired
    private SpringTemplateEngine templateEngine;


    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }


    @Async
    public void sendEmail(SimpleMailMessage email) {
        try {
            javaMailSender.send(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getHtmlContent(SimpleMailMessage mail) {
        Context context = new Context();
        Map<String, Object> map = new HashMap<>();
        map.put("subject", mail.getSubject());
        map.put("message", mail.getText());
        map.put("emailAddress", mail.getTo());
        context.setVariables(map);
        return templateEngine.process(map.toString(), context);
    }
}