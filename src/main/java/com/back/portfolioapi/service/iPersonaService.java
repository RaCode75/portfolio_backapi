
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Persona;
import java.util.List;

/**
 *
 * @author RaCode75
 */
public interface iPersonaService {
    
    public List<Persona> lookPersonas();
    
    public void addPersona(Persona per);
    
    public void deletePersona(Long id);
    
    public Persona findPersona(Long id);
    
    
    
}
