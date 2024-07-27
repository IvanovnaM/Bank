package com.example.bank.model;

public class UserLogIn {
    private String email;
    private int pin;

    public UserLogIn(String email, int pin) {
        this.email = email;
        this.pin = pin;
    }

    public UserLogIn() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }
}
