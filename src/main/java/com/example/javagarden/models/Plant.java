package com.example.javagarden.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
public class Plant extends NamedEntity {


    @ManyToOne
    @NotNull(message = "Plant Time is required")
    private PlantTime plantTime;

    public Plant( PlantTime plantTime) {
        this.plantTime = plantTime;
    }

    public Plant() {
    }

    public PlantTime getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(PlantTime plantTime) {
        this.plantTime = plantTime;
    }
}

