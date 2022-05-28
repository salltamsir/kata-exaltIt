package com.exaltit.kata.application.service;

import com.exaltit.kata.application.domain.Account;
import com.exaltit.kata.application.domain.AccountNotFoundException;
import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.domain.OperationType;
import com.exaltit.kata.application.port.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    public Operation deposit(String accountId, BigDecimal transactionAmount) {
        Account account = getAccountPort.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException(accountId));

        BigDecimal previousAmount = account.getBalance();

        account.deposit(transactionAmount);

        Operation operation = new Operation(account.getId(), LocalDateTime.now(), OperationType.DEPOSIT, previousAmount, account.getBalance(), transactionAmount, true);

        saveAccountPort.save(account);
        saveOperationPort.save(operation);

        return operation;

    }

    @Override
    public Operation withdraw(String accountId, BigDecimal transactionAmount) {
        Account account = getAccountPort.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException(accountId));

        BigDecimal previousAmount = account.getBalance();

        boolean isSuccess = account.withdraw(transactionAmount);
        Operation operation = new Operation(account.getId(), LocalDateTime.now(), OperationType.WITHDRAW, previousAmount, account.getBalance(), transactionAmount, isSuccess);
        if (isSuccess) {
            saveAccountPort.save(account);
        }
        saveOperationPort.save(operation);
        return operation;
    }

    @Override
    public BigDecimal balance(String accountId) {
        Account account = getAccountPort.findById(accountId)
                .orElseThrow(()-> new AccountNotFoundException(accountId));

        return account.getBalance();
    }
}
