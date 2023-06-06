package com.example.javagarden.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PlantIcon extends AbstractEntity {


    @OneToMany(mappedBy = "plantIcon")
    private final List<Plant> plants = new ArrayList<>();



    private String src;

    private String description;


    public PlantIcon(String src, String description) {
        this.src = src;
        this.description = description;
    }

    public PlantIcon() {
    }


    public List<Plant> getPlants() {
        return plants;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

