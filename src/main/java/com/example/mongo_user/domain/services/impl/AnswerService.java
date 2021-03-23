package com.example.mongo_user.domain.services.impl;

import com.example.mongo_user.app.dtos.AnswerDTO;
import com.example.mongo_user.domain.entities.Answer;
import com.example.mongo_user.domain.entities.Question;
import com.example.mongo_user.domain.repositories.AnswerRepository;
import com.example.mongo_user.domain.services.IAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AnswerService extends CommonService implements IAnswerService {

  @Autowired
  private AnswerRepository answerRepository;


//  @Override
//  public Answer findOneById(int id) {
//    return answerRepository.findOneById(id);
//  }

  @Override
  public Iterable<Answer> findAll() {
    return answerRepository.findAll();
  }

  @Override
  public Optional<Answer> findById(int id) {
    return answerRepository.findById(id);
  }

  @Override
  public Answer save(Answer answer) {
    return answerRepository.save(answer);
  }

  @Override
  public void remove(int id) {
    answerRepository.deleteById(id);
  }

  @Override
  public void createAnswer(AnswerDTO answerDTO) {
    Answer answer = new Answer();
    answer.setAnswId((int) generateSequence(Answer.SEQUENCE_NAME));
    answer.setAnswer(answerDTO.getAnswer());
    answer.setStatus(answerDTO.getStatus());
    answerRepository.save(answer);
  }
}
