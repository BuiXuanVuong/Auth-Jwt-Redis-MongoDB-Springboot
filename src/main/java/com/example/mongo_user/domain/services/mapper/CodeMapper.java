package com.example.mongo_user.domain.services.mapper;

import com.example.mongo_user.app.dtos.CodeDTO;
import com.example.mongo_user.domain.entities.Code;
import com.example.mongo_user.domain.entities.Question;

import java.util.List;


public class CodeMapper {

  public CodeDTO toDTO(Code code) {
    CodeDTO result = new CodeDTO();
    result.setCode(code.getCode());
    result.setCodeId(code.getCodeId());
    result.setName(code.getName());
    result.setQuestions(code.getQuestions());
    return result;
  }

  public Code toEntity(CodeDTO dto) {
    Code result = new Code();
    result.setCode(dto.getCode());
    result.setName(dto.getName());
    result.setQuestions(dto.getQuestions());
    return result;
  }

  public Code toEntity(Code result, CodeDTO dto) {
    result.setQuestions(dto.getQuestions());
    result.setName(dto.getName());
    result.setCode(dto.getCode());
    return result;
  }

}
