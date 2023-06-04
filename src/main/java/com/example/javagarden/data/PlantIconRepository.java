package com.example.javagarden.data;

import com.example.javagarden.models.PlantIcon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlantIconRepository extends CrudRepository<PlantIcon, Integer> {


}
