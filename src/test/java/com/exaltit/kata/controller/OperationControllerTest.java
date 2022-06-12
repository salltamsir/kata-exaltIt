package com.exaltit.kata.controller;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.in.BalanceUseCase;
import com.exaltit.kata.application.port.in.DepositUseCase;
import com.exaltit.kata.application.port.in.GetOperationUseCase;
import com.exaltit.kata.application.port.in.WithdrawUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static com.exaltit.kata.utils.TestUtils.createWithdrawOperation;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class OperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    DepositUseCase depositUseCase;

    @MockBean
    WithdrawUseCase withdrawUseCase;

    @MockBean
    BalanceUseCase balanceUseCase;

    @MockBean
    GetOperationUseCase getOperationUseCase;


    @Test
    void should_return_operations() throws Exception {

        Operation operation = createWithdrawOperation();

        when(getOperationUseCase.findOperations(anyString())).thenReturn(Arrays.asList(operation));

        mockMvc.perform(get("/operations/account/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

    }
}
