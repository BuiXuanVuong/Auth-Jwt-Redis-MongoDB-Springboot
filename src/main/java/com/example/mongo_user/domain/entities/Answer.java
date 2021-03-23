package com.example.mongo_user.domain.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Answer {

  @Transient
  public static final String SEQUENCE_NAME = "answer_sequence";

  @Id
  private int answId;

  private String answer;

  private int status;

}
