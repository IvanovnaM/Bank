package com.example.bank.model;

public class Transfer {
    private Long senderAccountNumber;
    private int amount;
    private Long receiverAccountNumber;
    private int pin;


    public int getAmount() {
        return amount;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Long getSenderAccountNumber() {
        return senderAccountNumber;
    }

    public void setSenderAccountNumber(Long senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public Long getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(Long receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public Transfer() {
    }

    public Transfer(Long senderAccountNumber, Long receiverAccountNumber, int amount, int pin) {
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
        this.pin = pin;

    }
}
