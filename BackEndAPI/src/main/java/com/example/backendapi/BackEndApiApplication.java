package com.example.backendapi;

import com.example.backendapi.Abstractions.IFileStorageService;
import com.example.backendapi.Service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;


@SpringBootApplication
public class BackEndApiApplication {
    @Autowired
    private EmailService senderService;
    public static void main(String[] args) {
        SpringApplication.run(BackEndApiApplication.class, args);
    }


    @Bean
    CommandLineRunner init(IFileStorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
    @EventListener(ApplicationReadyEvent.class)
    public void triggerMail() throws MessagingException {
        senderService.sendSimpleEmail("trangphuonglam120@gmail.com",
                "This is email body",
                "This is email subject");

    }
}
