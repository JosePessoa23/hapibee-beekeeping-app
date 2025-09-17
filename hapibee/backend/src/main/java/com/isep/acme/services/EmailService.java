package com.isep.acme.services;

import com.isep.acme.model.Modelo.ApiarioDTO;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail(String toAddress, String subject, String content) throws MessagingException;
}
