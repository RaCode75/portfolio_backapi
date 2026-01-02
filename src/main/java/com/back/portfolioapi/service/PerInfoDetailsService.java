package com.back.portfolioapi.service;

import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PerInfoDetailsService implements UserDetailsService {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Persona persona = personaRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException("Usuario no encontrado: " + email)
            );
System.out.println("ROLES DB -> " + persona.getRoles());

        return User.builder()
            .username(persona.getEmail())
            .password(persona.getPassword())
            .roles(persona.getRoles())
            .build();
    }
    
}
