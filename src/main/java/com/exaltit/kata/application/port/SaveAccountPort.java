package com.exaltit.kata.application.port;


import com.exaltit.kata.application.domain.Account;

public interface SaveAccountPort {
    void save(Account account);

}
