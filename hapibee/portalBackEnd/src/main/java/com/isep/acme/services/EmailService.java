package com.isep.acme.services;

import javax.mail.MessagingException;

public interface EmailService {

    void sendEmail(String toAddress, String subject, String content) throws MessagingException;
}
