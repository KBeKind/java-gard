package com.example.javagarden.models;

import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
public class PlantTime extends AbstractEntity{

    @Size(min = 3, max = 20, message = "Name must be at least 3 characters long")
    private String name;

    @OneToMany(mappedBy = "plantTime")
    private final List<Plant> plants = new ArrayList<>();




    public PlantTime(@Size(min = 3, message = "Name must be at least 3 characters long") String name) {
        this.name = name;
    }

    public PlantTime() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Plant> getPlants() {
        return plants;
    }

    @Override
    public String toString() {
        return name;
    }


}

