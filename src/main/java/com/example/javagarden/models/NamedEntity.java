package com.example.javagarden.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass
public abstract class NamedEntity extends AbstractEntity {


   @NotBlank(message = "name is required")
   @Size(message = "must be 1 to 30 characters")
   private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

}