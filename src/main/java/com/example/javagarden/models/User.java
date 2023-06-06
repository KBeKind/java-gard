package com.example.javagarden.models;

import jakarta.persistence.*;

import jakarta.validation.constraints.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends AbstractEntity {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @NotNull
    private String username;

    @NotNull
    private String pwHash;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
    inverseJoinColumns =  @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();


//    @Valid
//    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    private UserGardenData userGardenData;

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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}