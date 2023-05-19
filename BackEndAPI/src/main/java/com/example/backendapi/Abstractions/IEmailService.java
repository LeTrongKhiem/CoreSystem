package com.example.backendapi.Abstractions;

import com.example.backendapi.Model.User;
import com.example.backendapi.ModelMapping.ExchangedBookModel;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface IEmailService {
    void sendMailVerification(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
    void sendMailVerification(ExchangedBookModel model) throws MessagingException, UnsupportedEncodingException;
}
