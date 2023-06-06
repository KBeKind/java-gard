package com.example.javagarden.models.dto;


import com.example.javagarden.models.PlantIcon;

public class PlantingDTO {


    private String name;

    private int plotId;

    private int gardenId;

    private PlantIcon plantIcon;

    public PlantingDTO(String name, int plotId, int gardenId, PlantIcon plantIcon) {
        this.name = name;
        this.plotId = plotId;
        this.gardenId = gardenId;
        this.plantIcon = plantIcon;
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

    public PlantIcon getPlantIcon() {
        return plantIcon;
    }

    public void setPlantIcon(PlantIcon plantIcon) {
        this.plantIcon = plantIcon;
    }
}


