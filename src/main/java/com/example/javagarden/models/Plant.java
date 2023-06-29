package com.example.javagarden.models;


import jakarta.persistence.*;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Plant extends NamedEntity {

    @OneToOne(cascade = CascadeType.ALL)
    private PlantTime plantTime;


    @ManyToOne
    @NotNull(message = "Plant Icon is required")
    private PlantIcon plantIcon;


    @OneToMany(mappedBy = "plant")
    private final List<Planting> plantings = new ArrayList<>();

    @ManyToOne
//    @NotNull(message = "User Garden Data is is required")
    private UserGardenData userGardenData;



    public Plant(PlantTime plantTime, PlantIcon plantIcon) {
        this.plantTime = plantTime;
        this.plantIcon = plantIcon;
    }

    public Plant() {
    }

    public PlantTime getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(PlantTime plantTime) {
        this.plantTime = plantTime;
    }

    public PlantIcon getPlantIcon() {
        return plantIcon;
    }

    public void setPlantIcon(PlantIcon plantIcon) {
        this.plantIcon = plantIcon;
    }

    public List<Planting> getPlantings() {
        return plantings;
    }

    public UserGardenData getUserGardenData() {
        return userGardenData;
    }

    public void setUserGardenData(UserGardenData userGardenData) {
        this.userGardenData = userGardenData;
    }
}

