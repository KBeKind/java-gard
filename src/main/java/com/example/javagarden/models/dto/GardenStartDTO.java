package com.example.javagarden.models.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GardenStartDTO {

    @NotBlank(message = "Garden name is required")
    String name;

    @NotNull(message = "bed number of at least 1 is required.")
    @Min(value = 1, message = "Each garden must have at least one bed")
    @Max(value = 100, message = "100 is the maximum beds per garden")
    int bedNum;

    public GardenStartDTO(String name, int bedNum) {
        this.name = name;
        this.bedNum = bedNum;
    }

    public GardenStartDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBedNum() {
        return bedNum;
    }

    public void setBedNum(int bedNum) {
        this.bedNum = bedNum;
    }
}
