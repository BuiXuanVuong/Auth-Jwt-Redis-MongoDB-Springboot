package com.example.mongo_user.app.dtos;

import com.example.mongo_user.domain.entities.Answer;
import com.example.mongo_user.domain.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class QuestionDTO {

  private int questId;
  private String name;
  private String question;
  private List<AnswerDTO> answerDTOS;
  private List<CodeDTO> codeDTOS;
  private int timeAnswer;

}
