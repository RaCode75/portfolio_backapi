package com.back.portfolioapi.repository;

import com.back.portfolioapi.model.Persona;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RaCode75
 */

@Repository
public interface PersonaRepository extends JpaRepository <Persona, Long> {     
      Optional<Persona> findByEmail(String username);
      
      
     
      /*@Query("SELECT firstname, lastname, email, fecha_nacimiento, nacionalidad, ocupacion, sobre_mi, imagen_perfil, reside_en FROM Persona per WHERE per.id =  : id" )*/
      
         
}
