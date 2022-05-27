package com.exaltit.kata.adapters.repository;


import com.exaltit.kata.application.domain.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IAccountRepository extends MongoRepository<Account, String> { }

