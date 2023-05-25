package com.example.javagarden.data;

import com.example.javagarden.models.AbstractEntity;
import com.example.javagarden.models.PlantTime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantTimeRepository extends CrudRepository<PlantTime, Integer> {







}
