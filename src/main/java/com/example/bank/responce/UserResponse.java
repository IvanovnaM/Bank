package com.example.bank.responce;
import java.math.BigDecimal;
import java.time.LocalDateTime;
public class UserResponse {
    public boolean success;
    public String name;
    public String email;
    public Long accountNumber;
    public BigDecimal balance;
    public String message;
    public String sessionId;


    public UserResponse(boolean success, String name, String email, Long accountNumber, String message,BigDecimal balance) {
        this.success = success;
        this.name = name;
        this.email = email;
        this.accountNumber = accountNumber;
        this.message = message;
        this.balance = balance;
    }
    public UserResponse(boolean success, String message){
        this.success = success;
        this.message = message;
    }

    public UserResponse(boolean success, String name, String email, Long accountNumber, BigDecimal balance, String message, String sessionId) {
        this.success = success;
        this.name = name;
        this.email = email;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.message = message;
        this.sessionId = sessionId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "success=" + success +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", accountNumber=" + accountNumber +
                ", message='" + message + '\'' +
                '}';
    }
}
