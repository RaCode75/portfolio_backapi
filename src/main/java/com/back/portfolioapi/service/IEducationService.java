
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Education;
import java.util.List;

/**
 *
 * @author RaCode75
 */
public interface IEducationService {
    
    public List<Education> getEducation();
    
    public void saveEducation(Education ed);
    
    public void deleteEducation(Long id);
    
    public Education findEducation(Long id);
    
    
}
