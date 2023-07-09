package com.example.javagarden.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


@Entity
public class Planting extends AbstractEntity{


    @Valid
    @NotNull
    @OneToOne
    private Plot plot;

//    @NotNull(message = "Selecting a plant is required")
    @ManyToOne
    private Plant plant;

    private LocalDate plantingDate;

    private LocalDate harvestStartDate;

    private LocalDate removeDate;

    private Integer daysUntilHarvestStartDate;

    private Integer daysUntilRemoveStartDate;


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

    public void setDaysUntilHarvestStartDate(Integer daysUntilHarvestStartDate) {
        this.daysUntilHarvestStartDate = daysUntilHarvestStartDate;
    }

    public void deleteDaysUntilHarvestStartDate() {
        this.daysUntilHarvestStartDate = null;
    }


    public long getDaysUntilRemoveStartDate() {
        return daysUntilRemoveStartDate;
    }

    public void setDaysUntilRemoveStartDate(Integer daysUntilRemoveStartDate) {
        this.daysUntilRemoveStartDate = daysUntilRemoveStartDate;
    }

    public void deleteDaysUntilRemoveStartDate() {
        this.daysUntilRemoveStartDate = null;
    }

    public void updateDaysUntilHarvestStartDate() {
        this.daysUntilHarvestStartDate = Math.toIntExact(ChronoUnit.DAYS.between(LocalDate.now(), getHarvestStartDate()));
    }
    public void updateDaysUntilRemoveStartDate() {
        this.daysUntilRemoveStartDate = Math.toIntExact(ChronoUnit.DAYS.between(LocalDate.now(), getRemoveDate()));
    }
}
