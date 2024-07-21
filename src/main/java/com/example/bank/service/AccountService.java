package com.example.bank.service;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.repository.AccountRepository;
import com.example.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Account createAccount(String beneficiaryName, String pin) {
        Account account = new Account();
        account.setBeneficiaryName(beneficiaryName);
        account.setPin(pin);
        account.setBalance(0);
        return accountRepository.save(account);
    }

    public void deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId).get();
        account.setBalance(account.getBalance() + amount);
        transactionRepository.save(new Transaction(account, amount, "DEPOSIT"));
        accountRepository.save(account);
    }

    public void withdraw(Long accountId, double amount, String pin) {
        Account account = accountRepository.findById(accountId).get();
        if (account.getPin().equals(pin) && account.getBalance() >= amount) {
            account.setBalance(account.getBalance() - amount);
            transactionRepository.save(new Transaction(account, amount, "WITHDRAWAL"));
            accountRepository.save(account);
        } else {
            throw new RuntimeException("Invalid PIN or insufficient funds.");
        }
    }

    public List<Account> getAccounts(String beneficiaryName) {
        return accountRepository.findByBeneficiaryName(beneficiaryName);
    }

    public List<Transaction> getTransactions(Long accountId) {
        return transactionRepository.findByAccountId(accountId);
    }
}
