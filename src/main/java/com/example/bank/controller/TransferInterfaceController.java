package com.example.bank.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.bank.controllerBehaviour.TransferInterface;
import com.example.bank.model.Transfer;
import com.example.bank.responce.TransferResponse;
import com.example.bank.service.AccountService;
import com.example.bank.service.UserService;
import com.example.bank.utility.PinValidator;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/user")
public class TransferInterfaceController implements TransferInterface{
    private final AccountService accountService;
    private final UserService userService;

    @Autowired
    public TransferInterfaceController(AccountService accountService, UserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }


    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transferMoney (@RequestBody Transfer transfer) {

        if (!PinValidator.compare(transfer.getPin(),
                userService.getUserByAccountNumber(transfer.getSenderAccountNumber()).getPIN())){
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body(new TransferResponse("Неправильный пароль"));
        }

        BigDecimal currentBalance = accountService.getAccountByAccountNumber(transfer.getSenderAccountNumber()).getBalance();

        if (currentBalance.compareTo(BigDecimal.valueOf(transfer.getAmount())) < 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new TransferResponse("Недостаточно на балансе"));
        }
        if (accountService.getAccountByAccountNumber(transfer.getSenderAccountNumber()) == null ||
                accountService.getAccountByAccountNumber(transfer.getReceiverAccountNumber()) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new TransferResponse("Аккаунт не найден"));
        }
        if (accountService.getAccountByAccountNumber(transfer.getSenderAccountNumber()) ==
                accountService.getAccountByAccountNumber(transfer.getReceiverAccountNumber())) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new TransferResponse("Нельзя переводить самому себе"));
        }

        try {
            accountService.transferMoney(
                    transfer.getSenderAccountNumber(),
                    transfer.getReceiverAccountNumber(),
                    BigDecimal.valueOf(transfer.getAmount()));

            return ResponseEntity.status(HttpStatus.OK)
                    .body(new TransferResponse("Транзакция прошла успешно"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new TransferResponse("Произошла ошибка при внесении денег"));
        }

    }
}
