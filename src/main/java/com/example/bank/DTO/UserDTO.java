package com.example.bank.DTO;

import com.example.bank.utility.AccountNumberGenerator;
import com.example.bank.BankApplication;


import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
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
