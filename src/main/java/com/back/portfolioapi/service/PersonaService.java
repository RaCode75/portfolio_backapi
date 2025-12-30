
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.repository.PersonaRepository;
import com.back.portfolioapi.service.IPersonaService;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


/**
 *
 * @author RaCode75
 */

@Service
@RequiredArgsConstructor
@Transactional 
public class PersonaService implements IPersonaService {
      
    private PersonaRepository perRepo;
    
    @Override
    public List<Persona> getPersonas() {
        return perRepo.findAll();
    }

 
    @Override
    public Persona savePersona(Persona per) {
       return perRepo.save(per);
    }

    @Override
    public void deletePersona(Long id) {
      perRepo.deleteById(id);
    }

    @Override
    public Persona findPersona(Long id) {
       return perRepo.findById(id)
        .orElseThrow(() ->
            new RuntimeException("Persona no encontrada con id: " + id)
        );
    } 

    
}
