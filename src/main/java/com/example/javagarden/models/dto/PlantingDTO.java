package com.example.javagarden.models.dto;


import com.example.javagarden.models.Plant;
import com.example.javagarden.models.PlantIcon;
import jakarta.validation.constraints.NotNull;

public class PlantingDTO {


    private String name;

    private int plotId;

    private int gardenId;

    @NotNull(message = "Selecting a Plant is required.")
    private int plantId;



    public PlantingDTO(String name, int plotId, int gardenId, int plantId, String errorMsg) {
        this.name = name;
        this.plotId = plotId;
        this.gardenId = gardenId;

        //new stuff below
        this.plantId = plantId;

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

    public int getPlantId() {
        return plantId;
    }

    public void setPlantId(int plantId) {
        this.plantId = plantId;
    }


}


