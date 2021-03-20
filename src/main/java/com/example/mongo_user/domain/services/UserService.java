package com.example.mongo_user.domain.services;

import com.example.mongo_user.app.dtos.LoginResponse;
import com.example.mongo_user.app.dtos.TokenRequest;
import com.example.mongo_user.app.dtos.UserDTO;
import com.example.mongo_user.domain.config.JwtTokenProvider;
import com.example.mongo_user.domain.entities.LoginInfo;
import com.example.mongo_user.domain.entities.User;
import com.example.mongo_user.domain.models.TokenInfo;
import com.example.mongo_user.domain.repositories.LoginInfoRepository;
import com.example.mongo_user.domain.repositories.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.ArrayList;
import java.util.Optional;

@Service
@Log4j2
public class UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private LoginInfoService loginInfoService;

  @Autowired
  private LoginInfoRepository loginInfoRepository;




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
    LoginInfo loginInfo = new LoginInfo();
    for (User item : userRepository.findAll()) {
      if (item.getUserName().equals(use)) {
        log.info("========== "+loginInfoRepository.findLoginInfoByNameAndStatus(item.getUserName(), 1));
        if (loginInfoRepository.findLoginInfoByNameAndStatus(item.getUserName(), 1) != null) {
//        if (loginInfoRepository.findLoginInfoByName(item.getUserName()) != null && loginInfoRepository.findLoginInfoByName(item.getUserName()).getStatus()==1) {
          cacheManager.deleteValue(loginInfoRepository.findLoginInfoByNameAndStatus(item.getUserName(), 1) .getToken_login());
          LoginInfo loginInfo1 = loginInfoRepository.findLoginInfoByNameAndStatus(item.getUserName(), 1);
          loginInfo1.setStatus(0);
          loginInfoRepository.deleteLoginInfoByNameAndStatus(item.getUserName(), 1);
          loginInfoRepository.save(loginInfo1);


//          if (loginInfo1.getStatus() == 1) {
//            cacheManager.deleteValue(loginInfo1.getToken_login());
//            loginInfo1.setStatus(0);
//            loginInfoRepository.delete(loginInfo1);
////            loginInfoRepository.save(loginInfo1);
//
//          }

        }
        String jwt = tokenProvider.generateToken(use);
        String refreshToken = tokenProvider.generateFreshToken(use);
//        String refreshToken = genRefreshToken();
        cacheManager.setTokenValue(refreshToken, use, pass);
        loginInfo.setId_login(item.getId());
        loginInfo.setToken_login(jwt);
        loginInfo.setStatus(1);
        loginInfo.setName(use);
        loginInfoRepository.save(loginInfo);
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