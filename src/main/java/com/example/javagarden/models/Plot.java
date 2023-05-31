package com.example.javagarden.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "plot")
public class Plot extends AbstractEntity{

        @ManyToOne
        @JoinColumn(name = "bed_id")
        @NotNull(message = "Bed is required")
        private Bed bed;


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

}


