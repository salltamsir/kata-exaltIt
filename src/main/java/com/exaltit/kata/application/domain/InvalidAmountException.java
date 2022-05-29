package com.exaltit.kata.application.domain;

import java.math.BigDecimal;

public class InvalidAmountException extends RuntimeException{

    public InvalidAmountException (BigDecimal transactionAmount){
        super("Amount : "+transactionAmount+" is invalid");
    }
}
