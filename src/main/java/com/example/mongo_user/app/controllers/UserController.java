package com.example.mongo_user.app.controllers;

import com.example.mongo_user.app.dtos.TokenRequest;
import com.example.mongo_user.app.dtos.UserDTO;
import com.example.mongo_user.domain.services.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@RestController
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/user")
  public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
    userService.createUser(userDTO);
    return ResponseEntity.ok(userDTO);
  }

  @PutMapping("api/user")
  public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
      userService.updateUser(userDTO);
      return ResponseEntity.ok(userDTO);
  }

  @GetMapping("/api/users")
  public ResponseEntity<?> getListUser(HttpServletRequest request, HttpServletResponse response) {
      ArrayList<UserDTO> userDTOS = userService.getAll();
      return ResponseEntity.ok(userDTOS);
  }

  @PostMapping("/login")
  public ResponseEntity Login(@RequestBody UserDTO userDTO) throws IOException, ServletException {
    return userService.login(userDTO.getUserName(), userDTO.getPassword());
  }

  @PostMapping("/api/refresh")
  public ResponseEntity<?> refreshToken(@RequestBody TokenRequest refreshToken) {
   return userService.refreshToken(refreshToken);
  }

  @DeleteMapping("api/user")
  public ResponseEntity<?> deleteUser(@RequestBody UserDTO user) {
    return userService.deleteUser(user.getUserName());
  }

  @PostMapping("/api/logout")
  public ResponseEntity Logout(@RequestHeader String token) {
    return userService.logout(token);
  }

}