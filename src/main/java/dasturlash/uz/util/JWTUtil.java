package dasturlash.uz.util;

import dasturlash.uz.DTO.JwtDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;


import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtil {
    private static final int tokenLiveTime = 1000 * 3600 * 24;
    private static final String secretKey = "MzJbay1qaHVkb3ktY29tcGxleC1zZWNyZXQta2V5LXN0cmluZw==";

    public static String encode(String username, Integer code){
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("code", code);

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenLiveTime))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public static JwtDTO decode(String token) {
        Claims claims = Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();

        String username = (String) claims.get("username");
        Integer code = (Integer) claims.get("code");
        return new JwtDTO(username, code);
    }


    private static SecretKey getSecretKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
