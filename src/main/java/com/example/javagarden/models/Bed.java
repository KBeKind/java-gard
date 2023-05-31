package com.example.javagarden.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "bed")
public class Bed extends NamedEntity{

    @ManyToOne
    @JoinColumn(name = "garden_id")
    @NotNull(message = "Garden is required")
    private Garden garden;

    @OneToMany(mappedBy = "bed", cascade = CascadeType.ALL)
    private final List<Plot> plots = new ArrayList<>();


    @NotNull(message = "Bed Width in Plots is required")
    @Min(value = 1, message = "Each bed must have at least one plot")
    @Max(value = 100, message = "100 is the maximum for width")
    private int bedWidthPlots;

    @NotNull(message = "Bed Length in Plots is required")
    @Min(value = 1, message = "Each bed must have at least one plot")
    @Max(value = 100, message = "100 is the maximum for length")
    private int bedLengthPlots;


    public Bed(String name, Garden garden, int bedWidthPlots, int bedLengthPlots, int plotTotal) {

        this.setName(name);
        this.garden = garden;
        this.bedWidthPlots = bedWidthPlots;
        this.bedLengthPlots = bedLengthPlots;
        createPlots(plotTotal);
    }

    public Bed () {}


    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }


    public void createPlots(int plotTotal){

        for (int i = 0; i < plotTotal; i++){
            addPlot();
        }

    }


    public int getBedWidthPlots() {
        return bedWidthPlots;
    }

    public void setBedWidthPlots(int bedWidthPlots) {
        this.bedWidthPlots = bedWidthPlots;
    }

    public int getBedLengthPlots() {
        return bedLengthPlots;
    }

    public void setBedLengthPlots(int bedLengthPlots) {
        this.bedLengthPlots = bedLengthPlots;
    }

    public void addPlot() {

        this.plots.add(new Plot(this));

    }

    public List<Plot> getPlots() {
        return plots;
    }

}

