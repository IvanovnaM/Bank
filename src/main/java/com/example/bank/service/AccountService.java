package com.example.bank.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bank.Exceptions.InsufficientBalanceException;
import com.example.bank.Exceptions.TransferFailedException;
import com.example.bank.Exceptions.WithdrawalFailedException;
import com.example.bank.model.Account;
import com.example.bank.repository.AccountRepository;

import javax.security.auth.login.AccountNotFoundException;
import java.math.BigDecimal;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    public Account getAccountByAccountNumber(Long accountNumber){
        return accountRepository.findAccountByAccountId(accountNumber);
    }
    public void transferMoney(Long sender, Long receiver, BigDecimal amount) throws AccountNotFoundException, InsufficientBalanceException, TransferFailedException {

        Account senderAccount = accountRepository.findAccountByAccountId(sender);
        Account receiverAccount = accountRepository.findAccountByAccountId(receiver);

        System.out.println(sender);
        System.out.println(receiver);

        if (senderAccount == null || receiverAccount == null) {
            throw new AccountNotFoundException("Аккаунты не найдены");
        }

        if (senderAccount.getBalance().compareTo(amount) < 0) {
            throw new InsufficientBalanceException("Недостаточно денег на балансе");
        }

        senderAccount.setBalance(senderAccount.getBalance().subtract(amount));
        receiverAccount.setBalance(receiverAccount.getBalance().add(amount));

        try {
            accountRepository.saveAndFlush(senderAccount);
            accountRepository.saveAndFlush(receiverAccount);
        } catch (Exception e) {
            throw new TransferFailedException("Ошибка транзацкии", e);
        }
    }


    public Account withDrawMoneyFromAccount(Long accountNumber,
                                            BigDecimal withdrawalAmount) throws
            AccountNotFoundException,
            InsufficientBalanceException,
            WithdrawalFailedException {

        Account account = accountRepository.findAccountByAccountId(accountNumber);

        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }

        BigDecimal currentBalance = account.getBalance();

        if (currentBalance.compareTo(withdrawalAmount) < 0) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        BigDecimal newBalance = currentBalance.subtract(withdrawalAmount);
        account.setBalance(newBalance);
        Account updatedAccount;

        try {
            updatedAccount = accountRepository.saveAndFlush(account);
        } catch (Exception e) {
            throw new WithdrawalFailedException("Withdrawal failed", e);
        }

        return updatedAccount;
    }

    public void createAccountForUser(Long accountNumber) {
        Account account = new Account();
        account.setBalance(BigDecimal.valueOf(10_000));
        account.setAccountId(accountNumber);
        accountRepository.save(account);
    }

    public Account depositMoneyToAccount(Long accountNumber, BigDecimal deposit) throws AccountNotFoundException, WithdrawalFailedException {
        {

            Account account = accountRepository.findAccountByAccountId(accountNumber);

            if (account == null) {
                throw new AccountNotFoundException("Аккаунт не найдет");
            }

            BigDecimal currentBalance = account.getBalance();

            BigDecimal newBalance = currentBalance.add(deposit);
            account.setBalance(newBalance);
            Account updatedAccount;

            try {
                updatedAccount = accountRepository.saveAndFlush(account);
            } catch (Exception e) {
                throw new WithdrawalFailedException("Ошибка внесения денег", e);
            }

            return updatedAccount;
        }
    }
}
