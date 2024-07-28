package com.example.bank.service;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountService accountService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAccountForUser() {
        Long accountId = 1L;
        accountService.createAccountForUser(accountId);

        ArgumentCaptor<Account> accountCaptor = ArgumentCaptor.forClass(Account.class);
        verify(accountRepository).save(accountCaptor.capture());

        Account savedAccount = accountCaptor.getValue();
        assertNotNull(savedAccount);
        assertEquals(accountId, savedAccount.getAccountId());
        assertEquals(BigDecimal.valueOf(10_000), savedAccount.getBalance());
    }


    @Test
    public void testWithdrawMoney_sufficientBalance() {
        Long accountId = 1L;
        BigDecimal withdrawalAmount = BigDecimal.valueOf(100);

        Account account = new Account();
        account.setAccountId(accountId);
        account.setBalance(BigDecimal.valueOf(500));

        when(accountRepository.findAccountByAccountId(accountId)).thenReturn(account);

        assertDoesNotThrow(() -> accountService.withDrawMoneyFromAccount(accountId, withdrawalAmount));

        assertEquals(BigDecimal.valueOf(400), account.getBalance());
    }


    @Test
    public void testTransferMoney_sufficientBalance() {

        Long senderAccountId = 1L;
        Long receiverAccountId = 2L;
        BigDecimal amount = BigDecimal.valueOf(100);

        Account senderAccount = new Account();
        senderAccount.setAccountId(senderAccountId);
        senderAccount.setBalance(BigDecimal.valueOf(1000));

        Account receiverAccount = new Account();
        receiverAccount.setAccountId(receiverAccountId);
        receiverAccount.setBalance(BigDecimal.valueOf(500));

        when(accountRepository.findAccountByAccountId(senderAccountId)).thenReturn(senderAccount);
        when(accountRepository.findAccountByAccountId(receiverAccountId)).thenReturn(receiverAccount);

        // Вызываем метод сервиса, проверять в Debug
        assertDoesNotThrow(() -> accountService.transferMoney(senderAccountId, receiverAccountId, amount));

        assertEquals(BigDecimal.valueOf(900), senderAccount.getBalance());
        assertEquals(BigDecimal.valueOf(600), receiverAccount.getBalance());
    }


}



