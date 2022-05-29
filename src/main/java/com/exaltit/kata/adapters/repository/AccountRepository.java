package com.exaltit.kata.adapters.repository;

import com.exaltit.kata.application.domain.Account;
import com.exaltit.kata.application.port.GetAccountPort;
import com.exaltit.kata.application.port.SaveAccountPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AccountRepository implements GetAccountPort, SaveAccountPort {

    private IAccountRepository repository;

    public AccountRepository(IAccountRepository repository) {
        this.repository = repository;
    }


    @Override
    public Optional<Account> findById(String id) {
        return repository.findById(id);
    }

    @Override
    public void save(Account bankAccount) {
        repository.save(bankAccount);
    }
}
