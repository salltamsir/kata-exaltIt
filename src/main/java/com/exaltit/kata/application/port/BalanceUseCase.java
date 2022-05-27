package com.exaltit.kata.application.port;

import java.math.BigDecimal;

public interface BalanceUseCase {
    BigDecimal balance(String id);

}
