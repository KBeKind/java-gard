package com.example.javagarden.models;

import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class PlantTime extends NamedEntity{


    @OneToMany(mappedBy = "plantTime")
    private final List<Plant> plants = new ArrayList<>();

//    @ManyToOne
//    @NotNull(message = "User Garden Data is is required")
//    private UserGardenData userGardenData;


//    public PlantTime(UserGardenData userGardenData) {
//        this.userGardenData = userGardenData;
//    }

    public PlantTime() {}



    public List<Plant> getPlants() {
        return plants;
    }

//
//    public UserGardenData getUserGardenData() {
//        return userGardenData;
//    }
//
//    public void setUserGardenData(UserGardenData userGardenData) {
//        this.userGardenData = userGardenData;
//    }

}

