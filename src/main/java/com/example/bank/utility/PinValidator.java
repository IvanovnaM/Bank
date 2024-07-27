package com.example.bank.utility;

public class PinValidator {
    private final int userPin;
    private final int responsePin;

    public PinValidator(int userPin, int responsePin) {
        this.userPin = userPin;
        this.responsePin = responsePin;
    }

    public static boolean compare(int userPin, int responsePin ){
        return userPin == responsePin;
    }
}
