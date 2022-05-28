package com.exaltit.kata.adapters.api;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.BalanceUseCase;
import com.exaltit.kata.application.port.DepositUseCase;
import com.exaltit.kata.application.port.WithdrawUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final WithdrawUseCase withdrawUseCase;
    private final DepositUseCase depositUseCase;
    private final BalanceUseCase balanceUseCase;

    public AccountController(BalanceUseCase balanceUseCase, DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase) {
        this.withdrawUseCase = withdrawUseCase;
        this.balanceUseCase = balanceUseCase;
        this.depositUseCase = depositUseCase;
    }

    @GetMapping(value = "/{id}/balance")
    public BigDecimal balance (@PathVariable final String id) {
        return balanceUseCase.balance(id);
    }

    @PostMapping(value = "/{id}/deposit/{transactionAmount}")
    @ResponseStatus(value = HttpStatus.OK)
    public Operation deposit(@PathVariable final String id, @PathVariable final BigDecimal transactionAmount) {
        return depositUseCase.deposit(id, transactionAmount);
    }

    @PostMapping(value = "/{id}/withdraw/{transactionAmount}")
    @ResponseStatus(value = HttpStatus.OK)
    public Operation withdraw(@PathVariable final String id, @PathVariable final BigDecimal transactionAmount) {
        return withdrawUseCase.withdraw(id, transactionAmount);
    }


}
