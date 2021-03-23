package com.example.mongo_user.domain.services.impl;

import com.example.mongo_user.domain.entities.Code;
import com.example.mongo_user.domain.repositories.CodeRepository;
import com.example.mongo_user.domain.services.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CodeService implements ICodeService {

  @Autowired
  private CodeRepository codeRepository;

  @Override
  public Iterable<Code> findAll() {
    return codeRepository.findAll();
  }

  @Override
  public Optional<Code> findById(int id) {
    return codeRepository.findById(id);
  }

  @Override
  public Code save(Code code) {
    return codeRepository.save(code);
  }

  @Override
  public void remove(int id) {
    codeRepository.deleteById(id);
  }

}
