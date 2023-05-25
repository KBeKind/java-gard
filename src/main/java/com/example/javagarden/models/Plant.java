package com.example.javagarden.models;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;



@Entity
public class Plant extends AbstractEntity {

    @Size(min = 3, max = 20, message = "Name must be at least 3 characters long")
    private String name;

    @ManyToOne
    @NotNull(message = "Plant Time is required")
    private PlantTime plantTime;

    public Plant(@Size(min = 3, message = "Name must be at least 3 characters long") String name, PlantTime plantTime) {
        this.name = name;
        this.plantTime = plantTime;
    }

    public Plant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public PlantTime getPlantTime() {
        return plantTime;
    }

    public void setPlantTime(PlantTime plantTime) {
        this.plantTime = plantTime;
    }
}

