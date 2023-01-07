
package com.back.portfolioapi.service;

/**
 *
 * @author RaCode75
 */
import com.back.portfolioapi.controller.AuthenticationRequest;
import com.back.portfolioapi.controller.AuthenticationResponse;
import com.back.portfolioapi.controller.RegisterRequest;
import com.back.portfolioapi.model.Persona;
import com.back.portfolioapi.model.Role;
import com.back.portfolioapi.repository.PersonaRepository;
import com.back.portfolioapi.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final PersonaRepository repository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {
    var per = Persona.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER)
        .build();
    repository.save(per);
    var jwtToken = jwtService.generateToken(per);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var per = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(per);
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}