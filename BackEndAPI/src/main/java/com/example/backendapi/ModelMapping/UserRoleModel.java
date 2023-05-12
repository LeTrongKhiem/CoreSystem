package com.example.backendapi.ModelMapping;

import com.example.backendapi.Model.Role;
import com.example.backendapi.Model.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class UserRoleModel {
    private UUID id;
    private User user;
    private Role role;
}
