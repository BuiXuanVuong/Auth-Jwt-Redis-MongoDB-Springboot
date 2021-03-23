package com.example.mongo_user.domain.services.mapper;

import com.example.mongo_user.app.dtos.AnswerDTO;
import com.example.mongo_user.domain.entities.Answer;

public class AnswerMapper {

  public AnswerDTO toDto(Answer entity) {
    AnswerDTO result = new AnswerDTO();
    result.setAnswId(entity.getAnswId());
    result.setAnswer(entity.getAnswer());
    result.setStatus(entity.getStatus());
    return result;
  }

  public Answer toEntity(AnswerDTO dto) {
    Answer result = new Answer();
    result.setAnswer(dto.getAnswer());
//    result.setAnswId(dto.getAnswId());
    result.setStatus(dto.getStatus());
    return result;
  }

//  public Answer toEntity(Answer result, AnswerDTO dto) {
//    result.setAnswer(dto.getAnswer());
//    result.setStatus(dto.getStatus());
//    return result;
//  }

}
