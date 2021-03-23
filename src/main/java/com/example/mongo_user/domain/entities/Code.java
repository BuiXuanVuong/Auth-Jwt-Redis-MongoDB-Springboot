package com.example.mongo_user.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Code {

  @Transient
  public static final String SEQUENCE_NAME = "code_sequence";

  @Id
  private int codeId;
  private String code;
  private String name;
  private List<Question> questions;


}
