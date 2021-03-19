package com.example.mongo_user.domain.services;

import com.example.mongo_user.app.dtos.LoginResponse;
import com.example.mongo_user.app.dtos.TokenRequest;
import com.example.mongo_user.app.dtos.UserDTO;
import com.example.mongo_user.domain.config.redisConfig.JwtTokenProvider;
import com.example.mongo_user.domain.entities.User;
import com.example.mongo_user.domain.models.TokenInfo;
import com.example.mongo_user.domain.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.ArrayList;

@Service
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private CacheManager cacheManager;

  public ArrayList<UserDTO> getAll() {
    ArrayList<UserDTO> userDTOS = new ArrayList<>();
    for (User item : userRepository.findAll()) {
      userDTOS.add(UserMapper.toUserDTO(item));
    }
    return userDTOS;
  }

  public void createUser(UserDTO userDTO) {
    User user = User.builder().id(userDTO.getId()).name(userDTO.getName()).userName(userDTO.getUserName()).password(userDTO.getPassword()).roleName(userDTO.getRoleName()).build();

    userRepository.save(user);
  }

  public ResponseEntity<?> login(String use, String pass) {
    for (User item : userRepository.findAll()) {
      if (item.getUserName().equals(use)) {
        String jwt = tokenProvider.generateToken(use);
        String refreshToken = tokenProvider.generateFreshToken(use);
//        String refreshToken = genRefreshToken();
        cacheManager.setTokenValue(refreshToken, use, pass);
        return ResponseEntity.ok(new LoginResponse(jwt, refreshToken));
      }
    }

    return null;
  }

  public ResponseEntity<?> refreshToken(TokenRequest refreshTokenRequest) {
    String refreshToken = refreshTokenRequest.getRefreshToken();
    TokenInfo tokenInfo = cacheManager.getTokenValue(refreshToken);
    User user = userRepository.findByUserName(tokenInfo.getUserName());
    if (user == null) {
      return null;
    } else {
      String jwt = tokenProvider.generateToken(user.getUserName());
      cacheManager.deleteTokenValue(refreshToken);
      String newRefreshToken = tokenProvider.generateFreshToken(user.getUserName());
//      String newRefreshToken = genRefreshToken();
      cacheManager.setTokenValue(newRefreshToken, tokenInfo.getUserName(), tokenInfo.getPassword());
      return ResponseEntity.ok(new LoginResponse(jwt, newRefreshToken));
    }
  }

  public ResponseEntity<?> logout(String token) {
//    System.out.println(token);
    cacheManager.deleteValue(token);
    return ResponseEntity.ok("logout");
  }


}



















//        String refreshToken = genRefreshToken();

//  protected String generateToken() {
//    String token = RandomStringUtils.randomAlphabetic(8);
//    return token;
//  }
//
//        cacheManager.setValue("token1", token);
//  protected String genRefreshToken() {
//    String token = RandomStringUtils.randomAlphabetic(25);
//    return token;
//  }
//        return ResponseEntity.ok("ok");
//        User user = userRepository.findByUserName(use);
//
//        System.out.println(user);
//        if(user !=null){
//            String token = generateToken();
//            System.out.println(token);
//            return token;
//        }