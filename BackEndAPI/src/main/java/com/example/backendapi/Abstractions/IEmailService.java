package com.example.backendapi.Abstractions;

import com.example.backendapi.Model.User;
import jakarta.mail.MessagingException;

import java.io.UnsupportedEncodingException;

public interface IEmailService {
    void sendMailVerification(User user, String siteURL) throws MessagingException, UnsupportedEncodingException;
}
