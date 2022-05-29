package com.exaltit.kata.service;

import com.exaltit.kata.application.domain.*;
import com.exaltit.kata.application.port.GetAccountPort;
import com.exaltit.kata.application.port.SaveAccountPort;
import com.exaltit.kata.application.port.SaveOperationPort;
import com.exaltit.kata.application.service.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static com.exaltit.kata.utils.TestUtils.createAccount;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @InjectMocks
    AccountService accountService;

    @Mock
    GetAccountPort getAccountPort;

    @Mock
    SaveAccountPort saveAccountPort;

    @Mock
    SaveOperationPort saveOperationPort;


    @Test
    void should_deposit_20() {

        //Given
        Account account = createAccount() ;

        ArgumentCaptor<Operation> operationArgumentCaptor = ArgumentCaptor.forClass(Operation.class);
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);

        when(getAccountPort.findById(anyString())).thenReturn(Optional.of(account));
        doNothing().when(saveAccountPort).save(any(Account.class));
        doNothing().when(saveOperationPort).save(any(Operation.class));

        //When
        Operation operation = accountService.deposit("test", BigDecimal.TEN);

        //Then

        //Account
        assertEquals(BigDecimal.valueOf(20), account.getBalance(), "should be equal");
        //Operation
        assertEquals(BigDecimal.TEN, operation.getPreviousBalance(),"");
        assertEquals(BigDecimal.valueOf(20), operation.getNewBalance(),"");
        assertEquals(BigDecimal.TEN, operation.getTransactionAmount(),"");
        assertEquals(OperationType.DEPOSIT, operation.getOperationType(), "should be equal");
        assertEquals(OperationStatus.SUCCESS, operation.getOperationStatus(), "should be equal");

        verify(saveOperationPort).save(operationArgumentCaptor.capture());
        verify(saveAccountPort).save(accountArgumentCaptor.capture());

    }


    @Test
    void should_withdraw_10() {

        //Given
        Account account = createAccount() ;

        ArgumentCaptor<Operation> operationArgumentCaptor = ArgumentCaptor.forClass(Operation.class);
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);


        when(getAccountPort.findById(anyString())).thenReturn(Optional.of(account));
        doNothing().when(saveAccountPort).save(any(Account.class));
        doNothing().when(saveOperationPort).save(any(Operation.class));

        //When
        Operation operation = accountService.withdraw("test", BigDecimal.TEN);

        //Then

        //Account
        assertEquals(BigDecimal.ZERO, account.getBalance(), "should be equal");

        //Operation
        assertEquals(BigDecimal.TEN, operation.getPreviousBalance(),"");
        assertEquals(BigDecimal.ZERO, operation.getNewBalance(),"");
        assertEquals(BigDecimal.TEN, operation.getTransactionAmount(),"");
        assertEquals(OperationType.WITHDRAW, operation.getOperationType(), "should be equal");
        assertEquals(OperationStatus.SUCCESS, operation.getOperationStatus(), "should be equal");

        verify(saveOperationPort).save(operationArgumentCaptor.capture());
        verify(saveAccountPort).save(accountArgumentCaptor.capture());


    }

    @Test
    void should_fail_withdraw() {

        //Given
        Account account = createAccount() ;

        when(getAccountPort.findById(anyString())).thenReturn(Optional.of(account));

        //When
        Operation operation = accountService.withdraw("test", BigDecimal.valueOf(300));

        //Then

        //Account
        assertEquals(BigDecimal.TEN, account.getBalance(), "should be equal");

        //Operation
        assertEquals(BigDecimal.TEN, operation.getPreviousBalance(),"");
        assertEquals(BigDecimal.TEN, operation.getNewBalance(),"");
        assertEquals(BigDecimal.valueOf(300), operation.getTransactionAmount(),"");
        assertEquals(OperationType.WITHDRAW, operation.getOperationType(), "should be equal");
        assertEquals(OperationStatus.FAIL, operation.getOperationStatus(), "should be equal");

    }

    @Test
    void should_return_10() {

        //Given
        Account account = createAccount() ;

        when(getAccountPort.findById(anyString())).thenReturn(Optional.of(account));

        //When
        BigDecimal balance = accountService.balance("test");

        //Then
        assertEquals(BigDecimal.TEN, balance, "should be equal");
    }

    @Test
    void should_throw_AccountNotFoundException(){
        //Given
        String message = "Account id : test is not found";
        Exception exception;
        when(getAccountPort.findById(anyString())).thenReturn(Optional.empty());

        //When
        exception = assertThrows(AccountNotFoundException.class, ()-> {
            accountService.withdraw("test",BigDecimal.valueOf(200));
        });

        //Then
        assertTrue(exception.getMessage().contains(message));


    }

    @Test
    void should_throw_InvalidAmountException(){
        //Given
        String message = "Amount should be greater than 0 but is [-200]";
        Exception exception;

        //When
        exception = assertThrows(InvalidAmountException.class, ()-> {
            accountService.withdraw("test",BigDecimal.valueOf(-200));
        });

        //Then
        assertTrue(exception.getMessage().contains(message));


    }



}

