package com.exaltit.kata.application.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

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

    public Operation(){
    }

    public void computeStatus(Boolean status){
        if(status){
            this.operationStatus = OperationStatus.SUCCESS;
        }
        else {
            this.operationStatus = OperationStatus.FAIL;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getPreviousBalance() {
        return previousBalance;
    }

    public void setPreviousBalance(BigDecimal previousBalance) {
        this.previousBalance = previousBalance;
    }

    public BigDecimal getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(BigDecimal newBalance) {
        this.newBalance = newBalance;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }
}


