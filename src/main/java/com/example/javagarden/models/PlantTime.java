package com.example.javagarden.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;


@Entity
public class PlantTime extends AbstractEntity{


    @OneToOne
    private Plant plant;

    @ManyToOne
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

