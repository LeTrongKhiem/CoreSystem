package com.example.backendapi.ModelMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExchangedBookModel {
    private String nameUserCreated;
    private String nameBook;
    private String emailUserCreated;

    private String nameUserRequest;
    private String nameEmailRequest;
    private String phoneNumber;
}
