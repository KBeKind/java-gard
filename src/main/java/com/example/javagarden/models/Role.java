package com.example.javagarden.models;

import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
@NoArgsConstructor
public class Role extends NamedEntity {

    @OneToMany(mappedBy = "role")
    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }
}
