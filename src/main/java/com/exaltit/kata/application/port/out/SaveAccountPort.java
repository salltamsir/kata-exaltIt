package com.exaltit.kata.application.port.out;


import com.exaltit.kata.application.domain.Account;

public interface SaveAccountPort {
    void save(Account account);

}
