package com.example.javagarden.models;


import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
//import jakarta.validation.constraints.NotNull;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//public class UserGardenData extends AbstractEntity {
//
//
////    @Valid
//    @NotNull
//    @OneToOne
//    private UserEntity userEntity;
//
//    @OneToMany(mappedBy = "userGardenData")
//    private final List<PlantTime> plantTimes = new ArrayList<>();
//
//    public UserGardenData(UserEntity userEntity) {
//        this.userEntity = userEntity;
//    }
//
//    public UserGardenData() {
//    }
//
//    public UserEntity getUser() {
//        return userEntity;
//    }
//
//    public void setUser(UserEntity userEntity) {
//        this.userEntity = userEntity;
//    }
//}
