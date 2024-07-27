package com.example.bank.Exceptions;
import java.io.IOException;

public class InsufficientBalanceException extends IOException {
    public InsufficientBalanceException(String message){
        super(message);
    }

}
