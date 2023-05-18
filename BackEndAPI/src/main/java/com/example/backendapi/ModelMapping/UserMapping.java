package com.example.backendapi.ModelMapping;

import com.example.backendapi.Model.RegisterRequest;
import com.example.backendapi.Model.Role;
import com.example.backendapi.Model.User;
import com.example.backendapi.Model.UserRole;

import java.util.UUID;

public class UserMapping {
    public static User mapToUser(RegisterRequest request, String passwordHash) {
        User user = new User();
        UUID id = UUID.randomUUID();
        user.setId(id);
        user.setUserName(null);
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordHash);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setAddress(request.getAddress());
        user.setDob(request.getDateOfBirth());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setGender(request.getGender());
        user.setBlock(false);
        user.setEmailVerified(false);
        user.setUserType(1);
        return user;
    }

    public static UserRole mapToRole(User user, Role role) {
        UUID id = UUID.randomUUID();
        UserRole roleCreate = new UserRole();
        roleCreate.setId(id);
        roleCreate.setRole(role);
        roleCreate.setUser(user);
        return roleCreate;
    }
}
