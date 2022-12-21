
package com.back.portfolioapi.controller;

import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.service.iPersonaService;
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
public class Controller {
    
    @Autowired
    private iPersonaService iperService;
    
    @PostMapping ("/new/persona")
         public void addPersona(@RequestBody Persona per){
                iperService.addPersona(per);
            }
            
     @GetMapping("ver/personas")
     @ResponseBody
        public List<Persona> verPersonas(){
            return iperService.lookPersonas();
        }
        
     @DeleteMapping("delete/{id}")
        public void deletePersona(@PathVariable Long id){
            iperService.deletePersona(id);
        }
        
     @GetMapping("find/{id}")
        public void findPersona(@PathVariable Long id){
            iperService.findPersona(id);
        }
        
        
    
}
