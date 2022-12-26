
package com.back.portfolioapi.controller;

import com.back.portfolioapi.model.Education;
import com.back.portfolioapi.service.IEducationService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EducationController {
    @Autowired
    private IEducationService iedServ;
    
     @PostMapping ("education/new")
         public String addEducation(@RequestBody Education ed){
               iedServ.saveEducation(ed);
                return "Se añadio una Education correctamente";
            }
         
     @GetMapping("education")
     @ResponseBody
        public List<Education> verEducaton(){
            return iedServ.getEducation();
        }
        
     @DeleteMapping("education/delete/{id}")
        public String deletePersona(@PathVariable Long id){
            iedServ.deleteEducation(id);
                return "Se elimino una Education correctamente";
        }
        
     @GetMapping("education/find/{id}")
        public void findEducation(@PathVariable Long id){
            iedServ.findEducation(id);
        }
        
     @PutMapping("education/edit/{id}")
        public Education editEducation(@PathVariable Long id,
                                                          @RequestParam("titulo") String newTitulo,
                                                          @RequestParam("imagen") String newImg,
                                                          @RequestParam("carrera") String newCarrera,
                                                          @RequestParam("codigo") String newCodigo,
                                                          @RequestParam("inicio") String newInicio,
                                                          @RequestParam("fin") String newFin ){
            
         Education ed = iedServ.findEducation(id);
                ed.setTitulo(newTitulo);
                ed.setImagen(newFin);
                ed.setCarrera(newCarrera);
                ed.setCodigo(newCodigo);
                ed.setInicio(newInicio);
                ed.setFin(newFin);
                
         iedServ.saveEducation(ed);
         return ed;
        }
    
}
