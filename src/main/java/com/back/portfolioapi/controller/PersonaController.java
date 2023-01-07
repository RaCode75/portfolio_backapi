
package com.back.portfolioapi.controller;

import com.back.portfolioapi.model.Persona;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.back.portfolioapi.service.IPersonaService;
import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author RaCode75
 */

@RestController
@CrossOrigin(origins = "*")
public class PersonaController {
    
    @Autowired
    private IPersonaService iperService;
    
    
    @PostMapping ("persona/new")
         public String addPersona(@RequestBody Persona per){
                iperService.savePersona(per);
                return "Se agrego una Persona correctamente";
            }
         

            
     @GetMapping("persona/all")
     @ResponseBody
        public List<Persona> verPersonas(){
            return iperService.getPersonas();
        }
        
     @DeleteMapping("persona/delete/{id}")
        public String deletePersona(@PathVariable Long id){
            iperService.deletePersona(id);
                return "Se elimino una Persona correctamente";
        }
        
     @GetMapping("persona/find/{id}")
     @ResponseBody
        public Persona findPersona(@PathVariable Long id){
           return iperService.findPersona(id);
            
        }
        
   /*   @PutMapping("persona/edit/{id}")
        public Persona editPersona(@PathVariable Long id,
                                                    @RequestParam("firstname") String newFirstName,
                                                    @RequestParam("lastname") String newLastName,
                                                    @RequestParam("fecha_nacimiento") String newFechaNac,
                                                    @RequestParam("nacionalidad") String newNacionalidad,
                                                    @RequestParam("ocupacion") String newOcupacion,
                                                    @RequestParam("email") String newEmail,
                                                    @RequestParam("sobre_mi") String newSobreMi,
                                                    @RequestParam("imagen_perfil") String newImagen,
                                                    @RequestParam("reside_en") String newReside,
                                                    @RequestParam("password") String newPassword){

            Persona per = iperService.findPersona(id);
                    per.setFirstname(newFirstName);
                    per.setLastname(newLastName);
                    per.setFecha_nacimiento(newFechaNac);
                    per.setNacionalidad(newNacionalidad);
                    per.setOcupacion(newOcupacion);
                    per.setEmail(newEmail);
                    per.setSobre_mi(newSobreMi);
                    per.setImage_perfil(newEmail);
                    per.setReside_en(newImagen);
                    per.setPassword(newPassword);

               iperService.savePersona(per);
               
               return per;              
           
        }*/
        
        @PutMapping("persona/edit/{id}")
        public Persona editPersona(@RequestBody Persona per, @PathVariable Long id){
            Persona newPer = iperService.findPersona(id);

               newPer.setFirstname(per.getFirstname());
               newPer.setLastname(per.getLastname());
               newPer.setFecha_nacimiento(per.getFecha_nacimiento());
               newPer.setNacionalidad(per.getNacionalidad());
               newPer.setOcupacion(per.getOcupacion());
               newPer.setEmail(per.getEmail());
               newPer.setSobre_mi(per.getSobre_mi());
               newPer.setImage_perfil(per.getImage_perfil());
               newPer.setReside_en(per.getReside_en());
               newPer.setPassword(per.getPassword());
                    iperService.savePersona(per);
                    return newPer;
        }
        

        
    
}
