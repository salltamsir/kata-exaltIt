package com.exaltit.kata.application.port.out;


import com.exaltit.kata.application.domain.Operation;

public interface SaveOperationPort {
    void save(Operation operation);

}
