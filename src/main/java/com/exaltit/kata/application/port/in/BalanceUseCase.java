package com.exaltit.kata.application.port.in;

import java.math.BigDecimal;

public interface BalanceUseCase {
    /** Return the balance for the account with given id
     *
     * @param id
     * @return
     */
    BigDecimal balance(String id);

}
