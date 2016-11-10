package com.printkaari.message.service;

import com.printkaari.message.exception.MailNotSentException;
import com.printkaari.message.model.MailMessage;

public interface MailService {

    void sendTextMail(MailMessage mailMessage) throws MailNotSentException;

    void sendHtmlMail(MailMessage mailMessage) throws MailNotSentException;

}
