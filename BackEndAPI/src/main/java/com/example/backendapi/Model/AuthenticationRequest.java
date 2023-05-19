package com.example.backendapi.Model;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthenticationRequest {
    @NotEmpty(message = "Email is required")
    @Email(message = "Email is invalid")
    private String email;
    @NotEmpty(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    @Size(max = 20, message = "Password must be at most 20 characters")
    private String password;
}