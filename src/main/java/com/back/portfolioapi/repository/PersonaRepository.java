package com.back.portfolioapi.repository;

import com.back.portfolioapi.model.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RaCode75
 */

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Long> {

     
      
        @Query("select per from Persona per where per.email = ?1")
         Optional<Persona>  findByEmail(String email);
         
         Boolean existByEmail(String email);
         
}
