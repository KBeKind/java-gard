package com.example.javagarden.models;

import jakarta.persistence.Entity;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;


@Entity
public class PlantTime extends NamedEntity{


    @OneToMany(mappedBy = "plantTime")
    private final List<Plant> plants = new ArrayList<>();


    public PlantTime() {
    }

    public List<Plant> getPlants() {
        return plants;
    }




}

