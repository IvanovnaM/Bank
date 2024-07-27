package com.example.bank.utility;
import com.example.bank.DTO.UserDTO;

public class AccountNumberGenerator {
    public static Long generate(UserDTO userDTO){
        return (long) userDTO.hashCode();
    }
}
