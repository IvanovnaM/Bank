package com.example.bank.controllerBehaviour;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.bank.model.Transaction;
import com.example.bank.responce.TransactionResponse;

public interface DepositInterface {
    @PostMapping("/deposit")
    ResponseEntity<TransactionResponse> depositMoney(@RequestBody Transaction transaction);
}
