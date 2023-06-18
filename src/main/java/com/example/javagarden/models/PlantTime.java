package com.example.javagarden.models;

import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;


@Entity
public class PlantTime extends AbstractEntity{




    @OneToOne
    private Plant plant;

    @ManyToOne
//    @NotNull(message = "User Garden Data is is required")
    private UserGardenData userGardenData;


    private int daysUntilHarvestTotal;

    private int daysUntilPlantRemoveTotal;



    public PlantTime(UserGardenData userGardenData) {
        this.userGardenData = userGardenData;
    }

    public PlantTime() {}


    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public UserGardenData getUserGardenData() {
        return userGardenData;
    }

    public void setUserGardenData(UserGardenData userGardenData) {
        this.userGardenData = userGardenData;
    }

    public int getDaysUntilHarvestTotal() {
        return daysUntilHarvestTotal;
    }

    public void setDaysUntilHarvestTotal(int daysUntilHarvestTotal) {
        this.daysUntilHarvestTotal = daysUntilHarvestTotal;
    }

    public int getDaysUntilPlantRemoveTotal() {
        return daysUntilPlantRemoveTotal;
    }

    public void setDaysUntilPlantRemoveTotal(int daysUntilPlantRemoveTotal) {
        this.daysUntilPlantRemoveTotal = daysUntilPlantRemoveTotal;
    }
}

