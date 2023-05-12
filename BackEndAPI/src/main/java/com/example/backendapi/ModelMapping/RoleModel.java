package com.example.backendapi.ModelMapping;

import com.example.backendapi.Model.UserRole;
import jakarta.annotation.Nullable;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
public class RoleModel {
    private UUID id;

    private String name;

    private String description;

    private Set<UserRole> user_roles = new HashSet<>(0);
}
