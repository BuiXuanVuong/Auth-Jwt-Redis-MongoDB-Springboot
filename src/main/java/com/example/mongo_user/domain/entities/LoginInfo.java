package com.example.mongo_user.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "login-info")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginInfo {




  private Integer id_login;
  private String token_login;
  private String name;
//  private Date ExpiredJwt;
  private Integer status;

}
