package com.example.mongo_user.app.controllers;

import com.example.mongo_user.app.dtos.AnswerDTO;
import com.example.mongo_user.domain.services.impl.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AnswerController {

  @Autowired
  private AnswerService answerService;

  @PostMapping("/answer")
  public ResponseEntity<?> createAnswer(AnswerDTO answerDTO) {
    answerService.createAnswer(answerDTO);
    return ResponseEntity.ok(answerDTO);
  }
}
