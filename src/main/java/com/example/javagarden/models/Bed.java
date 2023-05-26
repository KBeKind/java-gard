package com.example.javagarden.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;


@Entity
public class Bed extends NamedEntity{
//    @NotNull(message = "Bed width is required")
//    private int bedWidth;
//
//    @NotNull(message = "Bed length is required")
//    private int bedLength;

    @ManyToOne
    @NotNull(message = "Garden is required")
    private Garden garden;


//    public Bed(int bedWidth, int bedLength, Garden garden) {
//        this.bedWidth = bedWidth;
//        this.bedLength = bedLength;
//        this.garden = garden;
//    }


    public Bed(Garden garden) {
        this.garden = garden;
    }

    public Bed () {}

//    public int getBedWidth() {
//        return bedWidth;
//    }
//
//    public void setBedWidth(int bedWidth) {
//        this.bedWidth = bedWidth;
//    }
//
//    public int getBedLength() {
//        return bedLength;
//    }
//
//    public void setBedLength(int bedLength) {
//        this.bedLength = bedLength;
//    }


    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }
}

