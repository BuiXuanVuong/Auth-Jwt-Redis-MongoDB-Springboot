package com.example.mongo_user.domain.config.redisConfig;

import com.example.mongo_user.domain.entities.User;
import com.example.mongo_user.domain.repositories.UserRepository;
import com.example.mongo_user.domain.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private UserService customUserDetailsService;

  @Autowired
  private UserRepository userRepository;



  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      try {
        String jwt = getJwtFromRequest(request);
        if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
          int userId = tokenProvider.getUserIdFromJWT(jwt);
          Optional<User> user = userRepository.findById(userId);

          if (user != null) {
            filterChain.doFilter(request, response);
          }

        }
      } catch (Exception  ex) {

      }
  }



  private String getJwtFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader("Authorization");
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
      return bearerToken.substring(7);
    }
    return null;
  }
}
