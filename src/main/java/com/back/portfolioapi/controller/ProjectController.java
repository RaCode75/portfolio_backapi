
package com.back.portfolioapi.controller;


import com.back.portfolioapi.model.Project;
import com.back.portfolioapi.service.IProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RaCode75
 */

@RestController
@CrossOrigin(origins = "*")
public class ProjectController {
    @Autowired
    private IProjectService iproServ;
    
    @PostMapping ("project/new")
         public String addProject(@RequestBody Project pro){
               iproServ.saveProject(pro);
                return "Se añadio un Project correctamente";
            }
         
     @GetMapping("project")
     @ResponseBody
        public List<Project> verProject(){
            return iproServ.getProject();
        }
        
     @DeleteMapping("project/delete/{id}")
        public String deleteProject(@PathVariable Long id){
            iproServ.deleteProject(id);
                return "Se elimino un Project correctamente";
        }
        
     @GetMapping("project/find/{id}")
        public void findProject(@PathVariable Long id){
            iproServ.findProject(id);
        }
        
     @PutMapping("project/edit/{id}")
        public Project editProject(@PathVariable Long id,
                                              @RequestParam("titulo") String newTitulo,
                                              @RequestParam("imagen") String newImagen,
                                              @RequestParam("description") String newDescription,
                                              @RequestParam("technologies") String newTechnologies,
                                              @RequestParam("repo") String newRepo,
                                              @RequestParam("site") String newSite){
            Project pro = iproServ.findProject(id);
            
                pro.setTitulo(newTitulo);
                pro.setImagen(newImagen);
                pro.setDescription(newDescription);
                pro.setTechnologies(newTechnologies);
                pro.setRepo(newRepo);
                pro.setSite(newSite);
                
                iproServ.saveProject(pro);
                
                return pro;
            
        }
    

    
}
