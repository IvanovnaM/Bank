package com.example.bank.responce;
import java.math.BigDecimal;

public class UserLogInResponse {
    private String name;
    private BigDecimal balance;
    private String message;

    public UserLogInResponse(String name, BigDecimal balance) {
        this.name = name;
        this.balance = balance;
    }
    public UserLogInResponse(String name, BigDecimal balance,String message) {
        this.name = name;
        this.balance = balance;
        this.message = getMessage();
    }

    public UserLogInResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserLogInResponse() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
