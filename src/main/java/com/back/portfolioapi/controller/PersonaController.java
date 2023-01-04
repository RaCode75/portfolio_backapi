
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 *
 * @author RaCode75
 */

@RestController
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
        public void findPersona(@PathVariable Long id){
            iperService.findPersona(id);
        }
        
      @PutMapping("persona/edit/{id}")
        public Persona editPersona(@PathVariable Long id,
                                                    @RequestParam("nombre") String newNombre,
                                                    @RequestParam("apellido") String newApellido,
                                                    @RequestParam("fecha_nacimiento") String newFechaNac,
                                                    @RequestParam("nacionalidad") String newNacionalidad,
                                                    @RequestParam("ocupacion") String newOcupacion,
                                                    @RequestParam("email") String newEmail,
                                                    @RequestParam("sobre_mi") String newSobreMi,
                                                    @RequestParam("imagen_perfil") String newImagen,
                                                    @RequestParam("reside_en") String newReside,
                                                    @RequestParam("pass") String newPass){
            
            Persona per = iperService.findPersona(id);
                    per.setNombre(newNombre);
                    per.setApellido(newApellido);
                    per.setFecha_nacimiento(newFechaNac);
                    per.setNacionalidad(newNacionalidad);
                    per.setOcupacion(newOcupacion);
                    per.setEmail(newEmail);
                    per.setSobre_mi(newSobreMi);
                    per.setImage_perfil(newEmail);
                    per.setReside_en(newImagen);
                    per.setPass(newPass);
               iperService.savePersona(per);
               
               return per;
        }
        
        
    
}
