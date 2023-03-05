
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.repository.PersonaRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 *
 * @author RaCode75
 */

@Service
@RequiredArgsConstructor
@Transactional 
public class PersonaService implements IPersonaService {
    
    @Autowired
    public PersonaRepository perRepo;
    
    @Override
    public List<Persona> getPersonas() {
        return perRepo.findAll();
    }

 
    @Override
    public Persona savePersona(Persona per) {
       return  perRepo.save(per);
    }

    @Override
    public void deletePersona(Long id) {
      perRepo.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
       return perRepo.findById( id).orElse(null);
    } 

/*    @Override
    public Persona findByEmail(String email) {
        return perRepo.findByEmail(email).orElseThrow(()
                -> new UsernameNotFoundException("User not found"));
    }*/

    
}
