package com.example.backendapi;

import com.example.backendapi.Abstractions.IFileStorageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BackEndApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackEndApiApplication.class, args);
    }


    @Bean
    CommandLineRunner init(IFileStorageService storageService) {
        return (args) -> {
            storageService.init();
        };
    }
}
