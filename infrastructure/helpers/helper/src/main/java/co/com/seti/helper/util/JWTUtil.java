package co.com.seti.helper.util;

import co.com.seti.usecase.user.jwt.gateway.JWTGateway;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Map;

@Component
public class JWTUtil implements JWTGateway {

    public String generateToken(String user, Map<String, Object> claims){

        Instant now = Instant.now();

        return Jwts.builder()
                .claims(claims)
                .subject(user)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plus(24L, ChronoUnit.HOURS)))
                .signWith( getKey() )
                .compact();

    }

    public String encritarPassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    public Jws<Claims> parseJwt(String jwtString) throws ExpiredJwtException,
            UnsupportedJwtException, MalformedJwtException, IllegalArgumentException {
        JwtParser jwtParser = Jwts.parser().verifyWith( getKey() ).build();
        return jwtParser.parseSignedClaims(jwtString);
    }

    private SecretKey getKey(){
        String claveSecreta = "secretsecretsecretsecretsecretsecretsecretsecret";
        byte[] secretKeyBytes = claveSecreta.getBytes();
        return Keys.hmacShaKeyFor(secretKeyBytes);
    }
}
