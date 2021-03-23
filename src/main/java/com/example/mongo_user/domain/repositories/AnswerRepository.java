package com.example.mongo_user.domain.repositories;

import com.example.mongo_user.app.dtos.AnswerDTO;
import com.example.mongo_user.domain.entities.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, Integer> {

//  Answer findOneById(int id);

//  void creatAnswer(AnswerDTO answerDTO);
}
