package com.example.bank.model;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    private double amount;
    private LocalDateTime timestamp;
    private String type; // "DEPOSIT", "WITHDRAWAL", "TRANSFER"

    // Конструкторы, геттеры и сеттеры
}
