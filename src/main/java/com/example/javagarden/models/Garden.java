package com.example.javagarden.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Garden extends NamedEntity{


//    @ManyToOne
//    @NotNull(message = "Plot Number is required")
//    private int plotNumber;

    public Garden () {}

//    public int getPlotNumber() {
//        return plotNumber;
//    }
//
//    public void setPlotNumber(int plotNumber) {
//        this.plotNumber = plotNumber;
//    }
}

