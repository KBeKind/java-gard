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


    public Planting(Plot plot) {
        this.plot = plot;
    }

    public Planting() {
    }

    public Plot getPlot() {
        return plot;
    }

    public void setPlot(Plot plot) {
        this.plot = plot;
    }
}
