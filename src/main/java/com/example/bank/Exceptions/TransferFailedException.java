package com.example.bank.Exceptions;
import java.io.IOException;

public class TransferFailedException extends IOException{
    public TransferFailedException(String message, Exception e){
        super(message);
    }
}
