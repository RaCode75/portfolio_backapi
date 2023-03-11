
package com.back.portfolioapi.controller;

import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.service.IPersonaService;
import com.back.portfolioapi.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;




/**
 *
 * @author RaCode75
 */

@RestController
@Data
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PersonaController {
    
    @Autowired
    private IPersonaService iperService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    
    
    @PostMapping ("/persona/auth/register")
         public String registerPersona ( @RequestBody Persona per){
                    per.setPassword(passwordEncoder.encode(per.getPassword()));
                     iperService.savePersona(per);
                     return "Se registró correctamente";
            }
         
         @PostMapping ("/persona/auth/authenticate")
           public String authenticateGetToken(
            @RequestBody AuthenticationRequest authRequest
                ) {
                  return jwtService.generateToken(authRequest.getEmail());
                }

            
     @GetMapping("/persona/all")
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     @ResponseBody
        public List<Persona> verPersonas(){
            return iperService.getPersonas();
        }
        
     @DeleteMapping("persona/delete/{id}")
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        public String deletePersona(@PathVariable Long id){
            iperService.deletePersona(id);
                return "Se elimino una Persona correctamente";
        }
        
     @GetMapping("persona/find/{id}")
     @PreAuthorize("hasAuthority('ROLE_ADMIN')")
     @ResponseBody
        public Persona findPersona(@PathVariable Long id){
           return iperService.findPersona(id);
            
        }
        

        
        @PutMapping("persona/edit/{id}")
        @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        public Persona replacePersona(@RequestBody Persona per, @PathVariable Long id){
            Persona newPer = iperService.findPersona(id); 

               newPer.setFirstname(per.getFirstname());
               newPer.setLastname(per.getLastname());
               newPer.setPassword(passwordEncoder.encode(per.getPassword()));
               newPer.setFecha_nacimiento(per.getFecha_nacimiento());
               newPer.setNacionalidad(per.getNacionalidad());
               newPer.setOcupacion(per.getOcupacion());
               newPer.setEmail(per.getEmail());
               newPer.setSobre_mi(per.getSobre_mi());
               newPer.setImage_perfil(per.getImage_perfil());
               newPer.setReside_en(per.getReside_en());
               newPer.setRoles(per.getRoles());
                    iperService.savePersona(newPer);
                    return newPer;
        }
        /*
        @PatchMapping("persona/edit/{id}")
        @PreAuthorize("hasAuthority('ROLE_ADMIN')")
        public Persona editPersona(@RequestBody Persona per, @PathVariable Long id){
            
        }*/
        

        
    
}
