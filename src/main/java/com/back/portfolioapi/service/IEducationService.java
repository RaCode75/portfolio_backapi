
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Education;
import java.util.List;

/**
 *
 * @author RaCode75
 */
public interface IEducationService {
    
    public List<Education> getEducation();
    
    public void addCurso(Education ed);
    
    public void deleteCurso(Long id);
    
    public Education findCurso(Long id);
    
    
}
