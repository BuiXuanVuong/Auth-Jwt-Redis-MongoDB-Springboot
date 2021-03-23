package com.example.mongo_user.domain.services;


import com.example.mongo_user.app.dtos.QuestionDTO;
import com.example.mongo_user.domain.entities.Question;

import java.util.List;

public interface IQuestionService extends GenaralService<Question>{

  public void creatQuestion(QuestionDTO questionDTO);

}
