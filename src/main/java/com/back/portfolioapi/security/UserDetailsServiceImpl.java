
package com.back.portfolioapi.security;

import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.repository.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author RaCode75
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	PersonaRepository perRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Persona per = perRepository.findByEmail(email)
		.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + email));

		return UserDetailsImpl.build(per);
	}

}