package com.exaltit.kata.application.port;


import com.exaltit.kata.application.domain.Operation;

import java.math.BigDecimal;

public interface DepositUseCase {
    Operation deposit(String id, BigDecimal amount);

}
