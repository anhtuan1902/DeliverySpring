/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tat.service.impl;

import com.tat.service.SendEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 *
 * @author trant
 */
@Service
public class SendEmailServiceImpl implements SendEmailService{
    
    @Autowired
    private JavaMailSender javaMailSenderImpl;

    @Override
    public void sendEmail(String userEmail, String result) {
        
        SimpleMailMessage newMail = new SimpleMailMessage();
        
        newMail.setTo(userEmail);
        newMail.setSubject("Kết quả đấu giá");
        newMail.setText("Hi,\nKết quả đấu giá của bạn là " + result);
        
        javaMailSenderImpl.send(newMail);
    }
    
}
