package com.example.bank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.bank.controllerBehaviour.WithDrawInterface;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.responce.TransactionResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.UserService;
import com.example.bank.utility.PinValidator;

import java.math.BigDecimal;


@RestController
@RequestMapping("/api/user")

public class UserWithDrawInterfaceController implements WithDrawInterface{
    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public UserWithDrawInterfaceController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/withDraw")
    public ResponseEntity<TransactionResponse> withDrawMoney(@RequestBody Transaction transaction) {
        if (!PinValidator.compare(transaction.getPin(), userService.getUserByAccountNumber(transaction.getAccountNumber()).getPIN())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new TransactionResponse("Неправильный пароль"));
        }
        BigDecimal currentBalance = accountService.getAccountByAccountNumber(transaction.getAccountNumber()).getBalance();

        if (currentBalance.compareTo(BigDecimal.valueOf(transaction.getAmount())) < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new TransactionResponse("Недостаточно на балансе"));
        }

        try {
            Account account = accountService.withDrawMoneyFromAccount(
                    transaction.getAccountNumber(),
                    BigDecimal.valueOf(transaction.getAmount()));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new TransactionResponse(
                            account.getAccountId(),
                            account.getBalance(),
                            "Транзакция прошла успешно"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TransactionResponse(
                            "Произошла ошибка при внесении денег"));
        }
    }
}
