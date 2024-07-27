package com.example.bank.model;
import jakarta.persistence.*;



@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int amount;
    private Long accountNumber;
    private int pin;

    public Transaction() {
    }

    public Transaction(int amount, Long accountNumber, int pin) {
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
