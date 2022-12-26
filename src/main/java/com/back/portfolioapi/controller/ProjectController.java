
package com.back.portfolioapi.controller;


import com.back.portfolioapi.model.Project;
import com.back.portfolioapi.service.IProjectService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author RaCode75
 */
@RestController
public class ProjectController {
    @Autowired
    private IProjectService iproServ;
    
    @PostMapping ("project/new")
         public String addProject(@RequestBody Project pro){
               iproServ.addProject(pro);
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
        
     @GetMapping("education/find/{id}")
        public void findProject(@PathVariable Long id){
            iproServ.findProject(id);
        }    
    
}
