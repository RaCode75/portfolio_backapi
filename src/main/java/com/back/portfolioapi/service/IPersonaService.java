
package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Persona;
import java.util.List;

/**
 *
 * @author RaCode75
 */
public interface IPersonaService {
    
    public List<Persona> getPersonas();
    
    public void addPersona(Persona per);
    
    public void deletePersona(Long id);
    
    public Persona findPersona(Long id);
}
