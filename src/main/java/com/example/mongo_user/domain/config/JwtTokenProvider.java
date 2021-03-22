package com.example.mongo_user.domain.config;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenProvider {

  private String JWT_SECRET = "vuong";

  public long JWT_EXPIRATION = 2 * 3600 * 1000;

  public String generateToken(String userName) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
    return Jwts.builder()
        .setHeader(header())
        .setClaims(getClaims(userName))
//        .setSubject(Long.toString(id))
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
        .compact();
  }

  public String generateFreshToken(String userName) {
    Date now = new Date();
    return Jwts.builder()
        .setHeader(header())
        .setClaims(getClaims(userName))
        .setIssuedAt(now)
        .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
        .compact();
  }


  public int getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser()
        .setSigningKey(JWT_SECRET)
        .parseClaimsJws(token)
        .getBody();

    return Integer.parseInt(claims.getSubject());
  }

  public boolean validateToken(String authToken) {
    try {
      Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
      return true;
    } catch (MalformedJwtException ex) {
      log.error("Invalid JWT token");
    } catch (ExpiredJwtException ex) {
      log.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      log.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      log.error("JWT claims string is empty.");
    }
    return false;
  }

  private Map<String, Object> getClaims(String userName) {
    Map<String, Object> mClaims = new HashMap<>();
    return mClaims;
  }

  private Map<String, Object> header() {
    Map<String, Object> map = new HashMap<>();
    map.put("typ", "JWT");
    return map;
  }
}

