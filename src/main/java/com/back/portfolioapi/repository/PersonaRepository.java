package com.back.portfolioapi.repository;

import com.back.portfolioapi.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RaCode75
 */

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Long> {
    
}