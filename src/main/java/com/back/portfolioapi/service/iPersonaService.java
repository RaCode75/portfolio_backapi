
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Persona;
import java.util.List;

/**
 *
 * @author RaCode75
 */
public interface IPersonaService {
    
    List<Persona> getPersonas();
    
    Persona savePersona(Persona per);
    
    void deletePersona(Long id);
    
    Persona findPersona(Long id);
    
    
    
}
