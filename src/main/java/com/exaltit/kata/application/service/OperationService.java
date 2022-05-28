package com.exaltit.kata.application.service;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.GetOperationPort;
import com.exaltit.kata.application.port.GetOperationUseCase;

import java.util.List;

public class OperationService implements GetOperationUseCase {

    private final GetOperationPort getOperationPort;

    public OperationService(GetOperationPort getOperationPort) {
        this.getOperationPort = getOperationPort;
    }

    @Override
    public List<Operation> findOperations(String accountId) {
        return getOperationPort.findAll(accountId);
    }
}
