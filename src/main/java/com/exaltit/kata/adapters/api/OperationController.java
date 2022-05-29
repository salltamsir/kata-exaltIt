package com.exaltit.kata.adapters.api;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.GetOperationUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/operations")
public class OperationController {

    private final GetOperationUseCase getOperationUseCase;

    private static final Logger LOG = Logger.getLogger(OperationController.class.getName());


    public OperationController(GetOperationUseCase getOperationUseCase) {
        this.getOperationUseCase = getOperationUseCase;
    }


    @GetMapping(value = "/account/{id}")
    public List<Operation> findOperations(@PathVariable final String id){
        LOG.log(Level.INFO, "Get operations request for account : ["+id+ "]");
        return getOperationUseCase.findOperations(id);
    }
}
