package com.example.mangxahoi.security.jwt;

import com.example.mangxahoi.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger( JwtProvider.class);
    private String jwtSecret = "trung.nguyen";   //chữ ký key mã hóa token
    private int jwtExpiration = 86600;           //thời gian sống của token

    public String createToken(Authentication authentication){
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date( new Date().getTime()+jwtExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken( String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token); // kiem tra token co dung ma hoa theo key cua minh khong
            return true;
        }
        catch (SignatureException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        }
        catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        }
        catch (MalformedJwtException e) {
            logger.error("Malformed JWT token -> Message: {}", e);
        }
        catch (IllegalArgumentException e) {
            logger.error("IllegalArgument JWT token -> Message: {}", e);
        }
        return false;
    }

    public String getUsernameFromToken(String token) {
        String username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return username;
    }
}
