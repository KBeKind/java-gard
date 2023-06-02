package com.example.javagarden.data;


import com.example.javagarden.models.Planting;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantingRepository extends CrudRepository<Planting, Integer> {

}
