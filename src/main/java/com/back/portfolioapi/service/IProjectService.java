
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Project;
import java.util.List;

/**
 *
 * @author RaCode75
 */
public interface IProjectService {
    
     public List<Project> getProject();
    
    public void addProject(Project pro);
    
    public void deleteProject(Long id);
    
    public Project findProject(Long id);
    
    public Project upgradeProject(Long id);
}
