package com.exaltit.kata.application.port;


import com.exaltit.kata.application.domain.Operation;

import java.math.BigDecimal;

public interface DepositUseCase {
    /** Method used to make a deposit
     *
     * @param id
     * @param amount
     * @return
     */
    Operation deposit(String id, BigDecimal amount);

}
