package com.example.javagarden.data;

import com.example.javagarden.models.Plant;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantRepository extends CrudRepository<Plant, Integer> {



}