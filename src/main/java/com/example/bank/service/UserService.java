package com.example.bank.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bank.DTO.UserDTO;
import com.example.bank.model.User;
import com.example.bank.repository.UserRepository;
import com.example.bank.responce.AccountDataResponse;
import com.example.bank.utility.DTOMapper;

import java.util.*;
import java.util.stream.Collectors;

@Service

public class UserService {
    private final UserRepository userRepository;
    private final AccountService accountService;

    @Autowired
    public UserService(UserRepository userRepository, AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    public User getUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }

    public User getUserByAccountNumber(Long accountNumber){
        return userRepository.findUserByAccount_AccountId(accountNumber);
    }

    public User createUserAccount(UserDTO userDTO) {
        accountService.createAccountForUser(userDTO.getAccountNumber());
        User user = DTOMapper.getUser(userDTO);
        userRepository.save(user);
        return user;
    }

    public List<AccountDataResponse> fetchAll() {
        return userRepository.findAll().stream()
                .map(user -> {
                    AccountDataResponse data = new AccountDataResponse();
                    data.setName(user.getName());
                    data.setAccountNumber(user.getAccount().getAccountId());
                    data.setBalance(user.getAccount().getBalance());
                    return data;
                })
                .collect(Collectors.toList());
    }
}
