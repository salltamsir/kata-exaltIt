package com.exaltit.kata.adapters.api;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.GetOperationUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final GetOperationUseCase getOperationUseCase;

    public OperationController(GetOperationUseCase getOperationUseCase) {
        this.getOperationUseCase = getOperationUseCase;
    }


    @GetMapping(value = "/account/{id}")
    public List<Operation> findOperations(@PathVariable final String id){
        return getOperationUseCase.findOperations(id);
    }
}
