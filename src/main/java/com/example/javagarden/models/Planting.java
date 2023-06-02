package com.example.javagarden.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;



@Entity
public class Planting extends AbstractEntity{


    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private Plot plot;


    private String plantName;

    public Planting(Plot plot, String plantName) {
        this.plot = plot;
        this.plantName = plantName;
    }

    public Planting() {
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }

    public String getPlantName() {
        return plantName;
    }

    public void setPlantName(String plantName) {
        this.plantName = plantName;
    }
}
