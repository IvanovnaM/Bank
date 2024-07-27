package com.example.bank.responce;
import java.math.BigDecimal;

public class AccountDataResponse {
    private String name;
    private Long accountNumber;
    private BigDecimal balance;

    public AccountDataResponse() {
    }

    public AccountDataResponse(String name, Long accountNumber, BigDecimal balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
