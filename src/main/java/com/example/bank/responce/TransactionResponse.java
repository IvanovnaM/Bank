package com.example.bank.responce;
import java.math.BigDecimal;

public class TransactionResponse {
    private Long accountNumber;
    private BigDecimal balance;
    private String message;

    public TransactionResponse(Long accountNumber, BigDecimal balance,String message) {

        this.accountNumber = accountNumber;
        this.balance = balance;
        this.message = message;
    }
    public TransactionResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TransactionResponse() {
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
