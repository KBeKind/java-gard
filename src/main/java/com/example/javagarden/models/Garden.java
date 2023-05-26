package com.example.javagarden.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Garden extends NamedEntity {

//    @NotNull(message = "Bed number is required")
//    private int gardenBedNumber;

    @OneToMany(mappedBy = "garden")
    private List<Bed> beds = new ArrayList<>();


//    public Garden(int gardenBedNumber) {
//        this.gardenBedNumber = gardenBedNumber;
//    }


    public Garden(List<Bed> beds) {
        this.beds = beds;
    }

    public Garden() {
    }

//    public int getGardenBedNumber() {
//        return gardenBedNumber;
//    }
//
//    public void setGardenBedNumber(int gardenBedNumber) {
//        this.gardenBedNumber = gardenBedNumber;
//    }


    public List<Bed> getBeds() {
        return beds;
    }

    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }
}


