package com.example.javagarden.data;


import com.example.javagarden.models.Plot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlotRepository extends CrudRepository<Plot, Integer> {

}
