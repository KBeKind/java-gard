package com.example.javagarden.models;

import com.example.javagarden.models.AbstractEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends AbstractEntity {

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

//    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private UserGardenData userGardenData;


    @ManyToOne
    @NotNull(message = "User Garden Data is is required")
    private Role role;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.pwHash = encoder.encode(password);
    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public UserGardenData getUserGardenData() {
        return userGardenData;
    }

    public void setUserGardenData(UserGardenData userGardenData) {
        this.userGardenData = userGardenData;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}