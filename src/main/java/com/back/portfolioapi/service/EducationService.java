
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Education;
import com.back.portfolioapi.repository.EducationRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RaCode75
 */
@Service
public class EducationService implements IEducationService{
    
    @Autowired
    public EducationRepository edRepo;

    @Override
    public List<Education> getEducation() {
        return edRepo.findAll();
    }

    @Override
    public void saveEducation(Education ed) {
        edRepo.save(ed);
    }

    @Override
    public void deleteEducation(Long id) {
        edRepo.deleteById(id);
    }

    @Override
    public Education findEducation(Long id) {
        return edRepo.findById(id).orElse(null);
    }   
}
