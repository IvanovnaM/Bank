package com.example.bank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bank.controllerBehaviour.DepositInterface;
import com.example.bank.model.Account;
import com.example.bank.model.Transaction;
import com.example.bank.responce.TransactionResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.UserService;
import com.example.bank.utility.PinValidator;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/user")
public class UserDepositControllerInterface implements DepositInterface{
    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public UserDepositControllerInterface(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }

    @PostMapping("/deposit")
    public ResponseEntity<TransactionResponse> depositMoney(Transaction transaction) {

        if (!PinValidator.compare(transaction.getPin(), userService.getUserByAccountNumber(transaction.getAccountNumber()).getPIN())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new TransactionResponse("Неправильный пароль"));
        }

        try {
            Account account = accountService.depositMoneyToAccount(
                    transaction.getAccountNumber(),
                    BigDecimal.valueOf(transaction.getAmount()));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new TransactionResponse(
                            account.getAccountId(),
                            account.getBalance(),
                            "Транзакция прошла успешно"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TransactionResponse("Произошла ошибка при внесении денег"));
        }
    }
}
