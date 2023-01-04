
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.repository.PersonaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author RaCode75
 */

@Service
public class PersonaService implements IPersonaService {
    
    @Autowired
    public PersonaRepository perRepo;

    @Override
    public List<Persona> getPersonas() {
        return perRepo.findAll();
    }

    @Override
    public void savePersona(Persona per) {
        perRepo.save(per);
    }

    @Override
    public void deletePersona(Long id) {
      perRepo.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
       return perRepo.findById( id).orElse(null);
    }

    @Override
    public Persona findPersona(String email) {
       return perRepo.findByEmail(email).orElse(null);
    }
    
     
}
