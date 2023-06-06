package com.example.javagarden.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;



@Entity
public class Planting extends AbstractEntity{


    @Valid
    @NotNull
    @OneToOne
    private Plot plot;


    @ManyToOne
    private Plant plant;


    public Planting(Plot plot, Plant plant) {
        this.plot = plot;
        this.plant = plant;

    }

    public Planting() {
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }



    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }
}
