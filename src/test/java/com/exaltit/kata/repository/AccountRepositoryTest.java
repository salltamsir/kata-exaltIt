package com.exaltit.kata.repository;


import com.exaltit.kata.adapters.repository.AccountRepository;
import com.exaltit.kata.adapters.repository.IAccountRepository;
import com.exaltit.kata.application.domain.Account;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.exaltit.kata.utils.TestUtils.createAccount;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AccountRepositoryTest {

    @InjectMocks
    private AccountRepository accountRepository;

    @Mock
    private IAccountRepository iAccountRepository;

    Account expectedAccount = createAccount();


    @Test
    void should_return_account() {
        //Given


        //WHen
        when(iAccountRepository.findById(anyString())).thenReturn(Optional.of(expectedAccount));
        Account account = accountRepository.findById("test").get();

        //Then
        assertEquals(expectedAccount, account, "should be equal");

    }

    @Test
    void should_save_account() {

        //given
        ArgumentCaptor<Account> accountArgumentCaptor = ArgumentCaptor.forClass(Account.class);

        //When
        when(iAccountRepository.save(any(Account.class))).thenReturn(expectedAccount);

        accountRepository.save(expectedAccount);

        //Then
        verify(iAccountRepository).save(accountArgumentCaptor.capture());
        assertEquals(expectedAccount, accountArgumentCaptor.getValue(), "should be equal");
    }

}
