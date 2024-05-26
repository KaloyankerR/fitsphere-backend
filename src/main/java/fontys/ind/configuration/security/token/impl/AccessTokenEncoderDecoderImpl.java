package fontys.ind.configuration.security.token.impl;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import fontys.ind.configuration.security.token.AccessToken;
import fontys.ind.configuration.security.token.AccessTokenDecoder;
import fontys.ind.configuration.security.token.AccessTokenEncoder;
import fontys.ind.configuration.security.token.exception.InvalidAccessTokenException;

import java.security.Key;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccessTokenEncoderDecoderImpl implements AccessTokenEncoder, AccessTokenDecoder {
    private final Key key;

    public AccessTokenEncoderDecoderImpl(@Value("${jwt.secret}") String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    @Override
    public String encode(AccessToken accessToken) {
        Map<String, Object> claimsMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(accessToken.getRoles())) {
            claimsMap.put("roles", accessToken.getRoles());
        }
        if (accessToken.getUserId() != null) {
            claimsMap.put("userId", accessToken.getUserId());
        }

        Instant now = Instant.now();
        return Jwts.builder()
                .setSubject(accessToken.getSubject())
                .setIssuedAt(Date.from(now))
                // .setExpiration(Date.from(now.plus(30, ChronoUnit.MINUTES)))
                .setExpiration(Date.from(now.plus(1, ChronoUnit.DAYS)))
                .addClaims(claimsMap)
                .signWith(key)
                .compact();
    }

    @Override
    public AccessToken decode(String accessTokenEncoded) {
        try {
            Jwt<?, Claims> jwt = Jwts.parserBuilder().setSigningKey(key).build()
                    .parseClaimsJws(accessTokenEncoded);
            Claims claims = jwt.getBody();

            // Roles should be retrieved as a List<String> since it's stored as an array
            List<String> roles = claims.get("roles", List.class);
            Long userId = claims.get("userId", Long.class);

            // Assuming there could be multiple roles, we concatenate them into a single string separated by commas
            String rolesAsString = String.join(",", roles);

            return new AccessTokenImpl(claims.getSubject(), userId, rolesAsString);
        } catch (JwtException e) {
            throw new InvalidAccessTokenException(e.getMessage());
        }
    }

}
