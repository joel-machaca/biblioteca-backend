package pe.edu.idat.biblioteca.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    @Value("${jwt.refresh.expiration}")
    private Long refreshExpiration;

    public Key getSigingKey(){

        return Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+expiration))
                .signWith(getSigingKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public String generateRefreshToken(String email){
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+refreshExpiration))
                .signWith(getSigingKey(), SignatureAlgorithm.HS256)
                .compact();
    }



    public String ExtractEmail(String token) {
        Claims claims=Jwts.parserBuilder()
                .setSigningKey(getSigingKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();


//        return Jwts.parserBuilder()
//                .setSigningKey(getSigingKey()) // aquí va la clave
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
    }

    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(getSigingKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        }catch (JwtException jwt){
            return false;
        }


    }


    //    public String ExtractEmail(String token){
//        return Jwts.parserBuilder()
//                .build()
//                .setSigningKey(getSigingKey())
//                .parseClaimsJwt(token)
//                .getBody()
//                .getSubject();
//    }
}
