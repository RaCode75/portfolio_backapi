package com.back.portfolioapi.repository;

import com.back.portfolioapi.model.Education;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author RaCode75
 */

@Repository
public interface EducationRepository extends JpaRepository <Education, Long>{
    
}
