
package com.back.portfolioapi.config;

import com.back.portfolioapi.config.JwtAuthFilter;
import com.back.portfolioapi.service.PerInfoDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 *
 * @author RaCode75
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class BasicAuthenticationConfig {
    
    @Autowired
    private JwtAuthFilter authFilter;   
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }    

    @Bean
    public  AuthenticationProvider authProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
    
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

      @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      return  http.cors().and().csrf().disable()
              .authorizeHttpRequests()
              .requestMatchers(
                "/persona/auth/register", 
                "/persona/auth/authenticate",
                "/education/find/**",
                "/project/find/**"
                ).permitAll()
              .and()
              .authorizeHttpRequests()
              .requestMatchers("/persona/**", "/education/**", "/project/**")
              .authenticated()
              .and()
              .sessionManagement()
              .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and()
               .authenticationProvider(authProvider())
               .addFilterBefore(authFilter,UsernamePasswordAuthenticationFilter.class)
               .build();              
       
    }
    
    
}
