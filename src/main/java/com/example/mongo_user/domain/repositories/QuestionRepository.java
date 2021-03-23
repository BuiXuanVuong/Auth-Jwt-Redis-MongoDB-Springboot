package com.example.mongo_user.domain.repositories;

import com.example.mongo_user.domain.entities.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, Integer> {

}
