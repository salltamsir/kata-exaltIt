package com.exaltit.kata.application.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
public  class Operation {


    private String id;
    private String accountId;
    private LocalDateTime date;
    private OperationType operationType;
    private BigDecimal previousBalance;
    private BigDecimal newBalance;
    private BigDecimal transactionAmount;
    private OperationStatus operationStatus;

    public Operation(String accountId, LocalDateTime date, OperationType operationType, BigDecimal previousBalance, BigDecimal newBalance, BigDecimal transactionAmount, boolean isSucess) {
        this.accountId = accountId;
        this.date = date;
        this.operationType = operationType;
        this.previousBalance = previousBalance;
        this.newBalance = newBalance;
        this.transactionAmount = transactionAmount;
        this.computeStatus(isSucess);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operation)) return false;
        Operation operation = (Operation) o;
        return Objects.equals(getId(), operation.getId()) &&
                Objects.equals(getAccountId(), operation.getAccountId()) &&
                Objects.equals(getDate(), operation.getDate()) &&
                getOperationType() == operation.getOperationType() &&
                Objects.equals(getPreviousBalance(), operation.getPreviousBalance()) &&
                Objects.equals(getNewBalance(), operation.getNewBalance()) &&
                Objects.equals(getTransactionAmount(), operation.getTransactionAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAccountId(), getDate(), getOperationType(), getPreviousBalance(), getNewBalance(), getTransactionAmount());
    }


    public void computeStatus(Boolean status){
        if(status){
            this.operationStatus = OperationStatus.SUCCESS;
        }
        else {
            this.operationStatus = OperationStatus.FAIL;
        }
    }

}


