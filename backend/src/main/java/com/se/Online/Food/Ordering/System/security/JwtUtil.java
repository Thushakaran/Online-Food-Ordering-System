//package com.se.Online.Food.Ordering.System.security;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.SignatureAlgorithm;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour expiration time
//    @Value("${jwt.secret-key}") // Secret key fetched from application.properties
//    private String secretKey;
//
//    // Generate JWT token for the username
//    public String generateToken(String username) {
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration time
//                .signWith(SignatureAlgorithm.HS256, secretKey) // Use the secret key to sign the token
//                .compact();
//    }
//
//    // Extract username from the JWT token
//    public String extractUsername(String token) {
//        return extractClaims(token).getSubject();
//    }
//
//    // Extract claims from the JWT token
//    private Claims extractClaims(String token) {
//        try {
//            return Jwts.parser()
//                    .setSigningKey(secretKey) // Use the secret key to validate the token
//                    .parseClaimsJws(token)
//                    .getBody();
//        } catch (Exception e) {
//            throw new RuntimeException("Invalid JWT token", e); // Handle invalid token format
//        }
//    }
//
//    // Validate if the token is valid for a given username
//    public boolean validateToken(String token, String username) {
//        return username.equals(extractUsername(token)) && !isTokenExpired(token);
//    }
//
//    // Check if the JWT token is expired
//    private boolean isTokenExpired(String token) {
//        return extractClaims(token).getExpiration().before(new Date());
//    }
//
//    // Refresh the JWT token if it's near expiration (Optional functionality)
//    public String refreshToken(String token) {
//        if (isTokenExpired(token)) {
//            throw new RuntimeException("Token has expired");
//        }
//        String username = extractUsername(token);
//        return generateToken(username);
//    }
//}


package com.se.Online.Food.Ordering.System.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour expiration time
    // Secret key generated using Keys.secretKeyFor(SignatureAlgorithm.HS256)
    private final Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    // Generate JWT token for the username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Set expiration time
                .signWith(secretKey) // Use the secure key to sign the token
                .compact();
    }

    // Extract username from the JWT token
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Extract claims from the JWT token
    private Claims extractClaims(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secretKey) // Use the secure key to validate the token
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new RuntimeException("Invalid JWT token", e); // Handle invalid token format
        }
    }

    // Validate if the token is valid for a given username
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // Check if the JWT token is expired
    private boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Refresh the JWT token if it's near expiration (Optional functionality)
    public String refreshToken(String token) {
        if (isTokenExpired(token)) {
            throw new RuntimeException("Token has expired");
        }
        String username = extractUsername(token);
        return generateToken(username);
    }
}
