package com.example.javagarden.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
public class Planting extends AbstractEntity{


    @Valid
    @NotNull
    @OneToOne
    private Plot plot;


    @ManyToOne
    private Plant plant;

    private LocalDate plantingDate;

    private LocalDate harvestStartDate;

    private LocalDate removeDate;

    private long daysUntilHarvestStartDate;

    private long daysUntilRemoveStartDate;

//    LocalDate dateOfJune15 = LocalDate.of(2023, 6, 15);
//    Period period = Period.between(today, dateOfJune15);
//


    public Planting(Plot plot, Plant plant, LocalDate plantingDate) {
        this.plot = plot;
        this.plant = plant;
        this.plantingDate = plantingDate;


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

    public LocalDate getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(LocalDate plantingDate) {
        this.plantingDate = plantingDate;
    }


    public LocalDate getHarvestStartDate() {
        return harvestStartDate;
    }

    public void setHarvestStartDate(LocalDate harvestStartDate) {
        this.harvestStartDate = harvestStartDate;
    }


    public void deleteHarvestStartDate() {

        this.harvestStartDate = null;

    }



    public LocalDate getRemoveDate() {
        return removeDate;
    }

    public void setRemoveDate(LocalDate removeDate) {
        this.removeDate = removeDate;
    }


    public void deleteRemoveDate() {

        this.removeDate = null;

    }

    public long getDaysUntilHarvestStartDate() {
        return daysUntilHarvestStartDate;
    }

    public void setDaysUntilHarvestStartDate(long daysUntilHarvestStartDate) {
        this.daysUntilHarvestStartDate = daysUntilHarvestStartDate;
    }

    public long getDaysUntilRemoveStartDate() {
        return daysUntilRemoveStartDate;
    }

    public void setDaysUntilRemoveStartDate(long daysUntilRemoveStartDate) {
        this.daysUntilRemoveStartDate = daysUntilRemoveStartDate;
    }

    public void updateDaysUntilHarvestStartDate() {
        this.daysUntilHarvestStartDate = ChronoUnit.DAYS.between(LocalDate.now(), getHarvestStartDate());
    }
    public void updateDaysUntilRemoveStartDate() {
        this.daysUntilRemoveStartDate = ChronoUnit.DAYS.between(LocalDate.now(), getRemoveDate());
    }
}
