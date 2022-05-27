package com.exaltit.kata.application.domain;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException (String accountId){
        super("Account id : "+accountId+" is not found");
    }
}
