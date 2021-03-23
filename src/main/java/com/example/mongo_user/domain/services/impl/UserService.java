package com.example.mongo_user.domain.services.impl;

import com.example.mongo_user.app.dtos.LoginResponse;
import com.example.mongo_user.app.dtos.TokenRequest;
import com.example.mongo_user.app.dtos.UserDTO;
import com.example.mongo_user.domain.config.JwtTokenProvider;
import com.example.mongo_user.domain.entities.LoginInfo;
import com.example.mongo_user.domain.entities.Sequence;
import com.example.mongo_user.domain.entities.User;
import com.example.mongo_user.domain.models.TokenInfo;
import com.example.mongo_user.domain.repositories.LoginInfoRepository;
import com.example.mongo_user.domain.repositories.UserRepository;
import com.example.mongo_user.domain.services.impl.CacheManager;
import com.example.mongo_user.domain.services.mapper.UserMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Service
@Log4j2
public class UserService extends CommonService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private CacheManager cacheManager;


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
    User user = new User();
    user.setId((int)generateSequence(User.SEQUENCE_NAME));
    user.setUserName(userDTO.getUserName());
    user.setPassword(userDTO.getPassword());
    user.setRoleName(userDTO.getRoleName());
    userRepository.save(user);
  }

  public void updateUser(UserDTO userDTO) {
    User user = userRepository.findOneById(userDTO.getId());
    user.setUserName(userDTO.getUserName());
    user.setPassword(userDTO.getPassword());
    user.setRoleName(userDTO.getRoleName());
    userRepository.save(user);
  }

  public ResponseEntity<?> login(String use, String pass) {
    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + tokenProvider.JWT_EXPIRATION);
    LoginInfo loginInfo = new LoginInfo();
      if (userRepository.findByUserNameAndPassword(use, pass)!=null) {
        User user = userRepository.findByUserNameAndPassword(use, pass);
        log.info(loginInfoRepository.findLoginInfoByNameAndStatus(use, 1));
        if (loginInfoRepository.findLoginInfoByNameAndStatus(use, 1) != null) {
          cacheManager.deleteValue(loginInfoRepository.findLoginInfoByNameAndStatus(use, 1) .getToken_login());
          LoginInfo loginInfo1 = loginInfoRepository.findLoginInfoByNameAndStatus(use, 1);
          loginInfo1.setStatus(0);
          loginInfo1.setExpiredJwt(expiryDate);
          loginInfoRepository.deleteLoginInfoByNameAndStatus(use, 1);
          loginInfoRepository.save(loginInfo1);
        }
        String jwt = tokenProvider.generateToken(use);
        String refreshToken = tokenProvider.generateFreshToken(use);
        cacheManager.setTokenValue(refreshToken, use, pass);
        loginInfo.setId_login(user.getId());
        loginInfo.setToken_login(jwt);
        loginInfo.setStatus(1);
        loginInfo.setName(use);
        loginInfo.setRoleName(user.getRoleName());
        loginInfo.setExpiredJwt(expiryDate);
        loginInfoRepository.save(loginInfo);
        return ResponseEntity.ok(new LoginResponse(jwt, refreshToken));
      }
    return ResponseEntity.ok(new String("Sai tài khoản, mật khẩu"));
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
      cacheManager.setTokenValue(newRefreshToken, tokenInfo.getUserName(), tokenInfo.getPassword());
      return ResponseEntity.ok(new LoginResponse(jwt, newRefreshToken));
    }
  }

  public ResponseEntity<?> deleteUser(String userName) {
    userRepository.deleteUserByUserName(userName);
    return ResponseEntity.ok("Delete success");
  }

  public ResponseEntity<?> logout(String token) {
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