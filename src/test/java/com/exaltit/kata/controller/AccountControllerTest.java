package com.exaltit.kata.controller;

import com.exaltit.kata.application.port.BalanceUseCase;
import com.exaltit.kata.application.port.DepositUseCase;
import com.exaltit.kata.application.port.GetOperationUseCase;
import com.exaltit.kata.application.port.WithdrawUseCase;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static com.exaltit.kata.utils.TestUtils.createDepositOperation;
import static com.exaltit.kata.utils.TestUtils.createWithdrawOperation;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
class AccountControllerTest {

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
    void should_deposit_200() throws Exception {

        when(depositUseCase.deposit(anyString(), any(BigDecimal.class))).thenReturn(createDepositOperation());


        mockMvc.perform(post("/account/1/deposit/400")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_withdraw_200() throws Exception {

        when(withdrawUseCase.withdraw(anyString(), any(BigDecimal.class))).thenReturn(createWithdrawOperation());

        mockMvc.perform(post("/account/1/withdraw/400")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void should_balance_10() throws Exception {

        when(balanceUseCase.balance(anyString())).thenReturn(BigDecimal.TEN);

        mockMvc.perform(get("/account/1/balance")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("10"));

    }
}
