package com.example.mongo_user.domain.services;

import java.util.Optional;

public interface GenaralService<T> {
  Iterable<T> findAll();

  Optional<T> findById(int id);

  T save(T t);

  void remove(int id);
}
