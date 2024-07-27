package com.example.bank.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "account")


public class Account {
    @Id
    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "createdAt")
    @CreationTimestamp
    private LocalDateTime createdAt;


    public Account() {
    }

    public Account(Long accountId, BigDecimal balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public Long getAccountId() {
        return accountId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setAccountId(Long accountNumberId) {
        this.accountId = accountNumberId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Account account = (Account) o;

        return Objects.equals(getAccountId(), account.getAccountId()) &&
                Objects.equals(getBalance(), account.getBalance());
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getAccountId(),
                getBalance());
    }

    @Override
    public String toString() {
        return "Account{" +
                ", accountNumber=" + accountId +
                ", balance=" + balance +
                '}';
    }



}
