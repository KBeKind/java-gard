package com.example.javagarden.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;

@Entity
public class UserGardenData extends AbstractEntity {


//    @Valid
    @NotNull
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "userGardenData")
    private final List<PlantTime> plantTimes = new ArrayList<>();

    public UserGardenData(User user) {
        this.user = user;
    }

    public UserGardenData() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
