
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Project;
import com.back.portfolioapi.repository.ProjectRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author RaCode75
 */
@Service
public class ProjectService implements IProjectService {

    @Autowired
    public ProjectRepository proRepo;
    
    @Override
    public List<Project> getProject() {
      return proRepo.findAll();
    }

    @Override
    public void saveProject(Project pro) {
       proRepo.save(pro);
    }

    @Override
    public void deleteProject(Long id) {
       proRepo.deleteById(id);
    }

    @Override
    public Project findProject(Long id) {
       return proRepo.findById(id).orElse(null);
    }    
}
