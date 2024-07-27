package com.example.bank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bank.controllerBehaviour.FetchAllInterface;
import com.example.bank.responce.AccountDataResponse;
import com.example.bank.service.UserService;


import java.util.List;

@RestController
@RequestMapping("/api/user")
public class FetchAllInterfaceAccountsController implements FetchAllInterface{
    private final UserService userService;

    @Autowired
    public FetchAllInterfaceAccountsController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<List<AccountDataResponse>> fetchAll() {
        return new ResponseEntity<>(userService.fetchAll(), HttpStatus.OK);
    }
}
