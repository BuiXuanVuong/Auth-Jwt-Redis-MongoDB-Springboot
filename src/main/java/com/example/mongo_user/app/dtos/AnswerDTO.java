package com.example.mongo_user.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AnswerDTO {

  private int answId;
  private String answer;
  private int status;


}
