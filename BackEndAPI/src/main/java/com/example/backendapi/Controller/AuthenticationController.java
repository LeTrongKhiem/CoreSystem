package com.example.backendapi.Controller;

import com.example.backendapi.Model.AppSettings;
import com.example.backendapi.Model.AuthenticationRequest;
import com.example.backendapi.Model.RegisterRequest;
import com.example.backendapi.Model.User;
import com.example.backendapi.Repository.UserRepository;
import com.example.backendapi.Service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

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

    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody @Valid AuthenticationRequest request, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid request");
        try {
            var result = authenticationService.authenticate(request);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(new String());
        }
    }
}
