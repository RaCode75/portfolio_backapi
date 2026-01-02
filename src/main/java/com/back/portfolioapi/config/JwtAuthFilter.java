
package com.back.portfolioapi.config;

import com.back.portfolioapi.service.JwtService;
import com.back.portfolioapi.service.PerInfoDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 *
 * @author RaCode75
 */
@Component
public class JwtAuthFilter extends OncePerRequestFilter {
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private PerInfoDetailsService userDetailsService;

    
    
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
         throws ServletException, IOException {

       String authHeader = request.getHeader("Authorization");
       final String jwt;
       final String email;
System.out.println("JWT FILTER EJECUTADO -> " + request.getRequestURI());

System.out.println("AUTH HEADER -> " + request.getHeader("Authorization"));


       if(authHeader == null || !authHeader.startsWith("Bearer ")){
           filterChain.doFilter(request, response);
           return;
       }

       jwt = authHeader.substring(7);
       email = jwtService.extractUsername(jwt);
       System.out.println("JWT EMAIL -> " + email);

       if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){
           UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                if (jwtService.validateToken(jwt, userDetails)) {
                    UsernamePasswordAuthenticationToken authToken =
                         new UsernamePasswordAuthenticationToken (
                                            userDetails,
                                            null,
                                            userDetails.getAuthorities()
                                        );

                    authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                    );
            System.out.println("AUTH -> " + authToken.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    
                        
                }
    }
       
       filterChain.doFilter(request, response);
    
    
    }
    
}
