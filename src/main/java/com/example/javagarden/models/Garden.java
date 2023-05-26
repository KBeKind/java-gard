package com.example.javagarden.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


@Entity
public class Garden extends NamedEntity{


    @NotNull(message = "Garden width is required")
    private int gardenWidth;

    @NotNull(message = "Garden length is required")
    private int gardenLength;


    public Garden(int gardenWidth, int gardenLength) {
        this.gardenWidth = gardenWidth;
        this.gardenLength = gardenLength;
    }

    public Garden () {}

    public int getGardenWidth() {
        return gardenWidth;
    }

    public void setGardenWidth(int gardenWidth) {
        this.gardenWidth = gardenWidth;
    }

    public int getGardenLength() {
        return gardenLength;
    }

    public void setGardenLength(int gardenLength) {
        this.gardenLength = gardenLength;
    }
}

