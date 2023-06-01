package com.example.javagarden.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "plot")
public class Plot extends AbstractEntity{

        @ManyToOne
        @JoinColumn(name = "bed_id")
        @NotNull(message = "Bed is required")
        private Bed bed;

        @OneToOne
        private Planting planting;


        public Plot(Bed bed) {
            this.bed = bed;
        }

        public Plot () {}

        public Bed getBed() {
            return bed;
        }

        public void setBed(Bed bed) {
            this.bed = bed;
        }


    public Planting getPlanting() {
        return planting;
    }

    public void setPlanting(Planting planting) {
        this.planting = planting;
    }


    @Override
    public String toString() {
        return "Plot{" +
                "bed=" + bed +
                ", planting=" + planting +
                '}';
    }
}


