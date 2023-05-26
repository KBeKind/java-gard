package com.example.javagarden.data;


import com.example.javagarden.models.Garden;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GardenRepository extends CrudRepository<Garden, Integer> {
}
