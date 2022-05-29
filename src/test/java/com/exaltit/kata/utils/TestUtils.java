package com.exaltit.kata.utils;

import com.exaltit.kata.application.domain.Account;
import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.domain.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class TestUtils {

    public static Account createAccount() {
        return new Account("test", BigDecimal.TEN);
    }

    public static Operation createDepositOperation() {
        return new Operation("test", LocalDateTime.now(), OperationType.DEPOSIT, BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.TEN, true);
    }

    public static Operation createWithdrawOperation() {
        return new Operation("test", LocalDateTime.now(), OperationType.WITHDRAW, BigDecimal.ZERO, BigDecimal.TEN, BigDecimal.TEN, true);
    }
}
