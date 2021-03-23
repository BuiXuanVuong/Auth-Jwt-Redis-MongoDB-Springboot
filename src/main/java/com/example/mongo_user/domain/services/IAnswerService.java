package com.example.mongo_user.domain.services;

import com.example.mongo_user.app.dtos.AnswerDTO;
import com.example.mongo_user.domain.entities.Answer;

import java.util.List;

public interface IAnswerService extends GenaralService<Answer> {

  public void createAnswer(AnswerDTO answerDTO);

}
