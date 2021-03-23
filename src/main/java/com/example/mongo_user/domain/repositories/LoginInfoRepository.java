package com.example.mongo_user.domain.repositories;

import com.example.mongo_user.domain.entities.LoginInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginInfoRepository extends MongoRepository<LoginInfo, Integer> {

  LoginInfo findLoginInfoByName (String name);
  void deleteLoginInfoByNameAndStatus(String name, Integer status);
  LoginInfo findLoginInfoByNameAndStatus(String name, Integer status);
//  LoginInfo findLoginInfoByToken_loginAndStatus(String token_login, Integer status);
}
