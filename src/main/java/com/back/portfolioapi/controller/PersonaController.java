
package com.back.portfolioapi.controller;

import com.back.portfolioapi.dto.AuthenticationRequest;
import com.back.portfolioapi.dto.AuthenticationResponse;
import com.back.portfolioapi.dto.RefreshTokenRequest;
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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
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
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private final UserDetailsService userDetailsService;

   
    
    @PostMapping ("/persona/auth/register")
         public String registerPersona ( @RequestBody Persona per){
                    per.setPassword(passwordEncoder.encode(per.getPassword()));
                     iperService.savePersona(per);
                     return "Se registró correctamente";
            }
         
         @PostMapping ("/persona/auth/authenticate")
           public AuthenticationResponse authenticate(
            @RequestBody AuthenticationRequest authRequest) {
               authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                     authRequest.getEmail(),
                     authRequest.getPassword()
                  )
               );
                  String accessToken = jwtService.generateAccessToken(authRequest.getEmail());
                  String refreshToken = jwtService.generateRefreshToken(authRequest.getEmail());
                  return new AuthenticationResponse(accessToken, refreshToken);
                }

         @PostMapping("/persona/auth/refresh")
         public AuthenticationResponse refreshTokAuthenticationResponse
            (@RequestBody RefreshTokenRequest request) {
             
            String refreshToken = request.getRefreshToken();
            String email = jwtService.extractUsername(refreshToken, true);
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            if(!jwtService.validateRefreshToken(refreshToken, userDetails)){
               throw new RuntimeException("Refresh token inválido");
            }
            
            String newAccessToken = jwtService.generateAccessToken(email);
            String newRefreshToken = jwtService.generateRefreshToken(email);
            
             return new AuthenticationResponse(newAccessToken, newRefreshToken);
         }
         

            
     @GetMapping("/persona/all")
     @PreAuthorize("hasRole('ADMIN')")
     @ResponseBody
        public List<Persona> verPersonas(){
            return iperService.getPersonas();
        }
        
     @DeleteMapping("persona/delete/{id}")
     @PreAuthorize("hasRole('ADMIN')")
        public String deletePersona(@PathVariable Long id){
            iperService.deletePersona(id);
                return "Se elimino una Persona correctamente";
        }
        
     @GetMapping("persona/find/{id}")
     @PreAuthorize("hasRole('ADMIN')")
     @ResponseBody
        public Persona findPersona(@PathVariable Long id){
           return iperService.findPersona(id);
            
        }
        

        
        @PutMapping("persona/edit/{id}")
        @PreAuthorize("hasRole('ADMIN')")
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
}
