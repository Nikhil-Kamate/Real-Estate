package com.crimsonlogic.realestate.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crimsonlogic.realestate.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long> {
    List<State> findAll();
    
    State findByName(String name);
}
