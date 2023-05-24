package com.example.javagarden.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


@Entity
public class Plant {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank(message = "name is required")
    @Size(min = 3, max = 50, message = "name must be between 3 and 50 characters")
    private String name;

    @NotBlank(message = "grow time is required")
    private int growTime;


    public Plant(String name, int growTime) {
        this.name = name;
        this.growTime = growTime;
    }

    public Plant() {}


    public int getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGrowTime() {
        return growTime;
    }

    public void setGrowTime(int growTime) {
        this.growTime = growTime;
    }


}

