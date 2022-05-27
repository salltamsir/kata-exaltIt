package com.exaltit.kata.application.service;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.*;

import java.math.BigDecimal;

public class AccountService implements DepositUseCase, WithdrawUseCase, BalanceUseCase {

    private GetAccountPort getAccountPort;
    private SaveAccountPort saveAccountPort;
    private SaveOperationPort saveOperationPort;

    public AccountService(GetAccountPort getAccountPort, SaveAccountPort saveAccountPort, SaveOperationPort saveOperationPort) {
        this.getAccountPort = getAccountPort;
        this.saveAccountPort = saveAccountPort;
        this.saveOperationPort = saveOperationPort;
    }


    @Override
    public BigDecimal balance(String id) {
        return null;
    }

    @Override
    public Operation deposit(String id, BigDecimal amount) {
        return null;
    }

    @Override
    public Operation withdraw(String id, BigDecimal amount) {
        return null;
    }
}
