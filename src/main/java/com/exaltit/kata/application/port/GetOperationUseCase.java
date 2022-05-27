package com.exaltit.kata.application.port;


import com.exaltit.kata.application.domain.Operation;

import java.util.List;

public interface GetOperationUseCase {
    List<Operation> findOperations(String id);

}
