package com.exaltit.kata.adapters.repository;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.out.GetOperationPort;
import com.exaltit.kata.application.port.out.SaveOperationPort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OperationRepository implements SaveOperationPort, GetOperationPort {

    private  IOperationRepository repository;

    public OperationRepository(IOperationRepository repository) {
        this.repository = repository;
    }


    @Override
    public void save(Operation operation) {
        repository.save(operation);
    }

    @Override
    public List<Operation> findAll(String id) {
        return repository.findByAccountId(id);
    }
}
