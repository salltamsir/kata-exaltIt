package com.exaltit.kata.application.port;


import com.exaltit.kata.application.domain.Operation;

import java.util.List;

public interface GetOperationPort {
    List<Operation> findAll(String id);

}
