package com.example.javagarden.controllers.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlantingDTO {


    String name;

    int plotId;

    int gardenId;

    public PlantingDTO(String name, int plotId, int gardenId) {
        this.name = name;
        this.plotId = plotId;
        this.gardenId = gardenId;
    }

    public PlantingDTO() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlotId() {
        return plotId;
    }

    public void setPlotId(int plotId) {
        this.plotId = plotId;
    }

    public int getGardenId() {
        return gardenId;
    }

    public void setGardenId(int gardenId) {
        this.gardenId = gardenId;
    }
}
