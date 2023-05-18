package com.example.backendapi.Model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class AppSettings {
    @Value(value = "${spring.mail.username}")
    private String email;
}

