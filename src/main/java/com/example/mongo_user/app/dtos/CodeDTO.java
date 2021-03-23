package com.example.mongo_user.app.dtos;

import com.example.mongo_user.domain.entities.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CodeDTO {

  private int codeId;
  private String code;
  private String name;
  private List<Question> questions;

}
