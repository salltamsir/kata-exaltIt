package com.exaltit.kata.adapters.repository;

import com.exaltit.kata.application.domain.Operation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IOperationRepository extends MongoRepository<Operation, String> {

    List<Operation> findByAccountId(String accountId);
}
