package com.example.mongo_user.domain.services.mapper;

import com.example.mongo_user.app.dtos.QuestionDTO;
import com.example.mongo_user.domain.entities.Question;

public class QuestionMapper {

  public QuestionDTO toDTO(Question entity) {
    QuestionDTO result = new QuestionDTO();
    result.setQuestId(entity.getQuestId());
    result.setAnswers(entity.getAnswers());
    result.setName(entity.getName());
    result.setQuestion(entity.getQuestion());
    result.setQuestions(entity.getQuestions());
    result.setTimeAnswer(entity.getTimeAnswer());
    return result;
  }

  public Question toEntity(QuestionDTO dto) {
    Question result = new Question();
    result.setAnswers(dto.getAnswers());
    result.setName(dto.getName());
    result.setQuestId(dto.getQuestId());
    result.setQuestion(dto.getQuestion());
    result.setQuestions(dto.getQuestions());
    result.setTimeAnswer(dto.getTimeAnswer());
    return result;
  }

  public Question toEntity(Question result, QuestionDTO dto) {
    result.setTimeAnswer(dto.getTimeAnswer());
    result.setQuestions(dto.getQuestions());
    result.setQuestion(dto.getQuestion());
    result.setName(dto.getName());
    result.setAnswers(dto.getAnswers());
    return result;
  }

}
