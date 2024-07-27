package com.example.bank.DTO;
import com.example.bank.BankApplication;
import com.example.bank.utility.AccountNumberGenerator;

import java.math.BigDecimal;
public class UserDTO extends AccountNumberGenerator{
    private Long id;
    private String email;
    private String name;
    private int pin;
    private Long accountNumber;
    private BigDecimal balance;

    public UserDTO(String name, Long accountNumber, BigDecimal balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public UserDTO(String email, String name, int pin) {
        this.email = email;
        this.name = name;
        this.pin = pin;
    }

    public UserDTO(Long id, String email, String name, int pin, Long accountNumber) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.pin = pin;
        this.accountNumber = accountNumber;
    }

    public UserDTO() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", pin=" + pin +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
