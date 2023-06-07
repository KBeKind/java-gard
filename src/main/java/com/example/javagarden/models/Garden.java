package com.example.javagarden.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Garden extends NamedEntity {

    @OneToMany(mappedBy = "garden", cascade = CascadeType.ALL)
    private final List<Bed> beds = new ArrayList<>();

    @ManyToOne
//    @NotNull(message = "User Garden Data is is required")
    private UserGardenData userGardenData;


    public Garden(String name) {
        this.setName(name);
    }

    public Garden() {
    }

    public void addBed(String bedName, int bedWidthPlots, int bedLengthPlots, int plotTotal) {

        this.beds.add(new Bed(bedName, this, bedWidthPlots, bedLengthPlots, plotTotal));

    }

    public List<Bed> getBeds() {
        return beds;
    }

    public UserGardenData getUserGardenData() {
        return userGardenData;
    }

    public void setUserGardenData(UserGardenData userGardenData) {
        this.userGardenData = userGardenData;
    }
}


