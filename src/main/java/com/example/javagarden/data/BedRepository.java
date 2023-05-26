package com.example.javagarden.data;


import com.example.javagarden.models.Bed;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedRepository extends CrudRepository<Bed, Integer> {

}
