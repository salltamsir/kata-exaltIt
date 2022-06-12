package com.exaltit.kata.application.port.in;

import com.exaltit.kata.application.domain.Operation;

import java.math.BigDecimal;

public interface WithdrawUseCase {

    /** Method used to make a withdraw
     *
     * @param id
     * @param amount
     * @return
     */
    Operation withdraw(String id, BigDecimal amount);

}
