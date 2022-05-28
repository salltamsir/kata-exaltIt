package com.exaltit.kata.repository;


import com.exaltit.kata.adapters.repository.IOperationRepository;
import com.exaltit.kata.adapters.repository.OperationRepository;
import com.exaltit.kata.application.domain.Operation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static com.exaltit.kata.utils.TestUtils.createDepositOperation;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OperationRepositoryTest {

    @InjectMocks
    private OperationRepository operationRepository;

    @Mock
    private IOperationRepository iOperationRepository;

    Operation operation = createDepositOperation();

    @Test
    void should_return_list() {


        //Given

        //When
        when(iOperationRepository.findByAccountId(anyString())).thenReturn(Arrays.asList(operation));
        List<Operation> operations = operationRepository.findAll("test");

        //Then
        assertEquals(1, operations.size());
        assertEquals(operation, operations.get(0));

    }


    @Test
    void should_save_operation() {

        //given
        ArgumentCaptor<Operation> accountArgumentCaptor = ArgumentCaptor.forClass(Operation.class);

        //When
        when(iOperationRepository.save(any(Operation.class))).thenReturn(operation);

        operationRepository.save(operation);

        //Then
        verify(iOperationRepository).save(accountArgumentCaptor.capture());
        assertEquals(operation, accountArgumentCaptor.getValue(), "should be equal");
    }
}
