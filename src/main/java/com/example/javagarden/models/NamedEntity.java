package com.example.javagarden.models;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@MappedSuperclass
public abstract class NamedEntity extends AbstractEntity {


   @NotBlank(message = "Name is required.")
//   @Size(min = 3, max = 30, message = "Name must be 3 to 30 characters.")
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