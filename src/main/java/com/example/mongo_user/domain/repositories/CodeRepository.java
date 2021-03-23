package com.example.mongo_user.domain.repositories;

import com.example.mongo_user.domain.entities.Code;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeRepository extends MongoRepository<Code, Integer> {
}
