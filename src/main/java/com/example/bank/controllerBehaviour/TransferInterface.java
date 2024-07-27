package com.example.bank.controllerBehaviour;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.bank.model.Transfer;
import com.example.bank.responce.TransferResponse;

public interface TransferInterface {
    @PostMapping("/transfer")
    ResponseEntity<TransferResponse> transferMoney(@RequestBody Transfer transfer);
}
