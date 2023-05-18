package com.example.backendapi.Controller;

import com.example.backendapi.Model.AppSettings;
import com.example.backendapi.Model.RegisterRequest;
import com.example.backendapi.Repository.UserRepository;
import com.example.backendapi.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000/"})
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    @Autowired
    UserRepository userService;
    @Autowired
    private Environment environment;
    @Autowired
    private AppSettings appSettings;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        String result = "User registered successfully";
        try {
            authenticationService.register(request, "http://localhost:3000/");
        } catch (HttpClientErrorException.BadRequest e) {
            result = e.getMessage();
        }
        return result;
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (authenticationService.verify(code)) {
            return "Verification successful";
        } else {
            return "Verification failed";
        }
    }
}
