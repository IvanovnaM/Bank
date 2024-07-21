package com.example.bank.controller;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/accounts")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @PostMapping
    public Account createAccount(@RequestParam String beneficiaryName, @RequestParam String pin) {
        return accountService.createAccount(beneficiaryName, pin);
    }

    @PostMapping("/{id}/deposit")
    public void deposit(@PathVariable Long id, @RequestParam double amount) {
        accountService.deposit(id, amount);
    }

    @PostMapping("/{id}/withdraw")
    public void withdraw(@PathVariable Long id, @RequestParam double amount, @RequestParam String pin) {
        accountService.withdraw(id, amount, pin);
    }

    @GetMapping("/{beneficiaryName}")
    public List<Account> getAccounts(@PathVariable String beneficiaryName) {
        return accountService.getAccounts(beneficiaryName);
    }

    @GetMapping("/{id}/transactions")
    public List<Transaction> getTransactions(@PathVariable Long id) {
        return accountService.getTransactions(id);
    }
}
