
package com.back.portfolioapi.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import static io.jsonwebtoken.Jwts.claims;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author RaCode75
 */
@Component
public class JwtService {
    
    private static final String SECRET = "3a8XnP1H5V9K0ZcXJ0cZ8Y3LwXH4u4ZzKpQxk2F8B0M=";
    private static final String REFRESH_SECRET = "QmFzZTY0U2VjdXJlUmVmcmVzaEtleTEyMzQ1Njc4OTA=";

    
    @PostConstruct
    public void printKeys() {
        System.out.println("ACCESS KEY -> " + SECRET);
        System.out.println("REFRESH KEY -> " + REFRESH_SECRET);
    }
    

    /*====================
    EXTRACT
    =====================*/
    public String extractUsername(String token){
        return extractUsername(token, false);
    }

    public String extractUsername(String token, boolean refresh){
        return extractClaim(token, Claims::getSubject, refresh);
    }
    
        public Date extractExpiration(String token, boolean refresh){
           return extractClaim(token, Claims::getExpiration, refresh);
    }
    
        public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, boolean refresh) {
            return claimsResolver.apply(extractAllClaims(token, refresh));
    }
        
     private Claims extractAllClaims(String token, boolean refresh) {
        return Jwts
                .parserBuilder()
                .setSigningKey(refresh ? getRefreshKey() : getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    
    /*===================
    EXPIRATION
    ====================*/
    private Boolean isTokenExpired(String token, boolean refresh) {
        return extractExpiration(token, refresh).before(new Date());
    }

    /*========================
    VALIDATE
    ==========================*/
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token, false);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token, false));
    }

    public boolean validateRefreshToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token,true);
        return username.equals(userDetails.getUsername())
            && !isTokenExpired(token, true);
    }

    /*============================
    GENERATE
    ===========================*/
   
    public String generateAccessToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 15))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String email){
        return Jwts.builder()
            .setSubject(email)
            .claim("type", "refresh")
            .setIssuedAt(new Date())
            .setExpiration(
                new Date(System.currentTimeMillis() + 1000L * 60 * 60 * 24 * 7)
            )
            .signWith(getRefreshKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    /*===============================
    KEYS
    ============================*/
    
    private Key getSignKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECRET));
    }

    private Key getRefreshKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(REFRESH_SECRET));
    }


    
}
