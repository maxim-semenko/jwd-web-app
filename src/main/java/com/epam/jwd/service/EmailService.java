package com.epam.jwd.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendMessage(String address) throws MessagingException;

}
