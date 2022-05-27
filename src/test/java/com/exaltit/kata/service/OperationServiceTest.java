package com.exaltit.kata.service;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.GetOperationPort;
import com.exaltit.kata.application.service.OperationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.exaltit.kata.utils.TestUtils.createDepositOperation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationServiceTest {

    @InjectMocks
    private OperationService operationService;

    @Mock
    GetOperationPort getOperationPort;

    @Test
    void should_return_operationList() {
        Operation operation = createDepositOperation();

        when(getOperationPort.findAll(anyString())).thenReturn(Arrays.asList(operation));

        List<Operation> operations = operationService.findOperations("test");

        assertEquals(1, operations.size());
        assertEquals(operation, operations.get(0));


    }
}
