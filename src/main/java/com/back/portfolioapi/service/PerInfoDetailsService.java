
package com.back.portfolioapi.service;

import com.back.portfolioapi.config.PerInfoDetails;
import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.repository.PersonaRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 *
 * @author RaCode75
 */
@Component
public class PerInfoDetailsService implements UserDetailsService {
    
    @Autowired
    private PersonaRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional<Persona> persona=repository.findByEmail(username);
        
        return persona.map(PerInfoDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User not found: " + username));
        
    }
    
   
}
