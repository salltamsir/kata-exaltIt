package com.exaltit.kata.adapters.api;

import com.exaltit.kata.application.domain.Operation;
import com.exaltit.kata.application.port.BalanceUseCase;
import com.exaltit.kata.application.port.DepositUseCase;
import com.exaltit.kata.application.port.WithdrawUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final WithdrawUseCase withdrawUseCase;
    private final DepositUseCase depositUseCase;
    private final BalanceUseCase balanceUseCase;

    private static final Logger LOG = Logger.getLogger(AccountController.class.getName());


    public AccountController(BalanceUseCase balanceUseCase, DepositUseCase depositUseCase, WithdrawUseCase withdrawUseCase) {
        this.withdrawUseCase = withdrawUseCase;
        this.balanceUseCase = balanceUseCase;
        this.depositUseCase = depositUseCase;
    }

    @GetMapping(value = "/{id}/balance")
    public BigDecimal balance (@PathVariable final String id) {
        LOG.log(Level.INFO, "Deposit request for account : ["+id+ "]");
        return balanceUseCase.balance(id);
    }

    @PostMapping(value = "/{id}/deposit/{transactionAmount}")
    @ResponseStatus(value = HttpStatus.OK)
    public Operation deposit(@PathVariable final String id, @PathVariable final BigDecimal transactionAmount) {
        LOG.log(Level.INFO, "Deposit request for account : ["+id+ "] - transaction amount : "+transactionAmount);
        return depositUseCase.deposit(id, transactionAmount);
    }

    @PostMapping(value = "/{id}/withdraw/{transactionAmount}")
    @ResponseStatus(value = HttpStatus.OK)
    public Operation withdraw(@PathVariable final String id, @PathVariable final BigDecimal transactionAmount) {
        LOG.log(Level.INFO, "Withdraw request for account : ["+id+ "] - transaction amount : "+transactionAmount);
        return withdrawUseCase.withdraw(id, transactionAmount);
    }


}
