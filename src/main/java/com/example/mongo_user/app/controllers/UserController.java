package com.example.mongo_user.app.controllers;

import com.example.mongo_user.app.dtos.TokenRequest;
import com.example.mongo_user.app.dtos.UserDTO;
import com.example.mongo_user.domain.entities.User;
import com.example.mongo_user.domain.models.TokenInfo;
import com.example.mongo_user.domain.repositories.UserRepository;
import com.example.mongo_user.domain.services.CacheManager;
import com.example.mongo_user.domain.services.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class UserController {
  @Autowired
  private UserService userService;

  @Autowired
  private CacheManager cacheManager;

  @Autowired
  private UserRepository userRepository;

  @PostMapping("/user")
  public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
    userService.createUser(userDTO);
    return ResponseEntity.ok(userDTO);
  }

  @GetMapping("/users")
  public ResponseEntity<?> getListUser() {
    ArrayList<UserDTO> userDTOS = userService.getAll();
    return ResponseEntity.ok(userDTOS);
  }

  @PostMapping("/login")
  public ResponseEntity Login(@RequestBody UserDTO userDTO) {
    return userService.login(userDTO.getUserName(), userDTO.getPassword());
  }

  @PostMapping("/refresh")
  public ResponseEntity<?> refreshToken(@RequestBody TokenRequest refreshToken) {
   return userService.refreshToken(refreshToken);
  }


  @PostMapping("/logout")
  public ResponseEntity Logout(@RequestHeader String token) {
    return userService.logout(token);
  }

}