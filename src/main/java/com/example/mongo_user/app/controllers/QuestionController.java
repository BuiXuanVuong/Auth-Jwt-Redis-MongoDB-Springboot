package com.example.mongo_user.app.controllers;

import com.example.mongo_user.app.dtos.QuestionDTO;
import com.example.mongo_user.domain.services.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class QuestionController {

  @Autowired
  private IQuestionService questionService;

  @PostMapping("/question")
  public ResponseEntity<?> createQuestion(@RequestBody QuestionDTO questionDTO) {

    questionService.creatQuestion(questionDTO);
    return ResponseEntity.ok(questionDTO);

  }


}
