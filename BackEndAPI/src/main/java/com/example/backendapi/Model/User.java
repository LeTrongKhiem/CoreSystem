package com.example.backendapi.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Nationalized;
import org.hibernate.annotations.Where;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "users")
//@Where(clause = "is_deleted = false")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String passwordHash;
    private String phoneNumber;
    private String address;
    private String email;
    private boolean isEmailVerified;
    private Date dob;
    private int gender;
    private boolean isBlock;
    @Column(name = "user_type", nullable = true)
    private int userType;
    @Nullable
    private Date lastLogin;
    @Transient
    private Collection<? extends GrantedAuthority> authorities;

    @Getter
    @Setter
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<UserRole> user_roles;
//    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Book book;

    public User(UUID id, String username, String email, String password, boolean isEmailVerified, String firstName, String lastName, String address, String phoneNumber) {
        this.id = id;
        this.userName = username;
        this.email = email;
        this.passwordHash = password;
        this.isEmailVerified = isEmailVerified;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public User(UUID id, String username, String email, String password, boolean isEmailVerified, String firstName,
                String lastName, String address, String phoneNumber, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = username;
        this.email = email;
        this.passwordHash = password;
        this.isEmailVerified = isEmailVerified;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.authorities = authorities;
    }

    public static User build(User user) {
        List<GrantedAuthority> authorityList = user.getUser_roles() != null ? user.getUser_roles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                .collect(Collectors.toList()) : Collections.emptyList();
        return new User(user.getId(), user.getUsername(), user.getEmail(), user.getPasswordHash(), user.isEmailVerified(),
                user.getFirstName(), user.getLastName(), user.getAddress(), user.getPhoneNumber(), authorityList);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorityList = getUser_roles() != null ? getUser_roles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRole().getName()))
                .collect(Collectors.toList()) : Collections.emptyList();
        return authorityList;
    }

    @Override
    public String getPassword() {
        return passwordHash;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEmailVerified;
    }
}
