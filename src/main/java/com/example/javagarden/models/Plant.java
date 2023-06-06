package com.example.javagarden.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Plant extends NamedEntity {

    @ManyToOne
    @NotNull(message = "Plant Time is required")
    private PlantTime plantTime;


    @ManyToOne
//    @NotNull(message = "Plant Icon is required")
    private PlantIcon plantIcon;


    @OneToMany(mappedBy = "plant")
    private final List<Planting> plantings = new ArrayList<>();




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
}

