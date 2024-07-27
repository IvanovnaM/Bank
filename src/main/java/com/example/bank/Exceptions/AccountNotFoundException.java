package com.example.bank.Exceptions;
import java.io.IOException;

public class AccountNotFoundException extends IOException{
    public AccountNotFoundException(String message, Exception e){
        super(message);
    }
}
