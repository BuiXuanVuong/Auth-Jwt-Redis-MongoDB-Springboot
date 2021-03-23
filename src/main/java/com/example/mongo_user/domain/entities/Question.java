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
public class Question {

  @Transient
  public static final String SEQUENCE_NAME = "question_sequence";

  @Id
  private int questId;

  private String name;
  private String question;
  private List<Answer> answers;
  private List<Question> questions;
  private int timeAnswer;

}
