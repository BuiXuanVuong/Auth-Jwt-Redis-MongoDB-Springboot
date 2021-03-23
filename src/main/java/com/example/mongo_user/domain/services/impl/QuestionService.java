package com.example.mongo_user.domain.services.impl;

import com.example.mongo_user.app.dtos.AnswerDTO;
import com.example.mongo_user.app.dtos.CodeDTO;
import com.example.mongo_user.app.dtos.QuestionDTO;
import com.example.mongo_user.domain.entities.Answer;
import com.example.mongo_user.domain.entities.Code;
import com.example.mongo_user.domain.entities.Question;
import com.example.mongo_user.domain.entities.User;
import com.example.mongo_user.domain.repositories.CodeRepository;
import com.example.mongo_user.domain.repositories.QuestionRepository;
import com.example.mongo_user.domain.services.IQuestionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class QuestionService extends CommonService implements IQuestionService {

  @Autowired
  private QuestionRepository questionRepository;

  @Autowired
  private CodeRepository codeRepository;

  @Override
  public Iterable<Question> findAll() {
    return questionRepository.findAll();
  }

  @Override
  public Optional<Question> findById(int id) {
    return questionRepository.findById(id);
  }

  @Override
  public Question save(Question question) {
    return questionRepository.save(question);
  }

  @Override
  public void remove(int id) {
    questionRepository.deleteById(id);
  }

  public void creatQuestion(QuestionDTO questionDTO) {
    Question question = new Question();
    question.setQuestId((int) generateSequence(Question.SEQUENCE_NAME));
    question.setName(questionDTO.getName());


    List<Answer> list = new ArrayList<>();
    for (int i = 0; i <questionDTO.getAnswerDTOS().size() ; i++) {
      AnswerDTO answerDTO = questionDTO.getAnswerDTOS().get(i);
      Answer answer = new Answer();
      answer.setStatus(answerDTO.getStatus());
      answer.setAnswId((int) generateSequence(Answer.SEQUENCE_NAME));
      answer.setAnswer(answerDTO.getAnswer());
      list.add(answer);
    }

    List<Code> listCode = new ArrayList<>();

    List<CodeDTO> ListCodeDTOByUserCreate = questionDTO.getCodeDTOS();
    int count = 0;
    for (CodeDTO eachCodeDtoInListCodeDto: ListCodeDTOByUserCreate) {
      if (!checkCodeInList(eachCodeDtoInListCodeDto.getCode())) {
        
      }
    }

    question.setAnswers(list);
    question.setTimeAnswer(questionDTO.getTimeAnswer());
    questionRepository.save(question);
  }

  boolean checkCodeInList(String code) {
    List<Code> checkListCode = codeRepository.findAll();
      for (Code eachCodeInList : checkListCode) {
        if (code.equals(eachCodeInList.getCode())) {
          return true;
        }
      }
      return false;
  }
}
