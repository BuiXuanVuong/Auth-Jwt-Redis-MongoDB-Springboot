package com.example.mongo_user.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

  @Transient
  public static final String SEQUENCE_NAME = "user_sequence";

  @Id
  private Integer id;
  private String name;
  private String userName;
  private String password;
  private String roleName;

}