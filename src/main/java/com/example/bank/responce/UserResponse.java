package com.example.bank.responce;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UserResponse {
    @Setter
    @Getter
    public boolean success;
    @Setter
    @Getter
    public String name;
    @Setter
    @Getter
    public String email;
    @Getter
    @Setter
    public Long accountNumber;
    @Setter
    @Getter
    public BigDecimal balance;
    @Getter
    @Setter
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

    public String getTimestamp() {
        return LocalDateTime.now().toString();
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
