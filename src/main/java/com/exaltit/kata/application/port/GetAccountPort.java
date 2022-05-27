package com.exaltit.kata.application.port;


import com.exaltit.kata.application.domain.Account;

import java.util.Optional;

public interface GetAccountPort {
    Optional<Account> findById(String id);

}
