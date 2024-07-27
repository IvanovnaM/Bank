package com.example.bank.controllerBehaviour;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.bank.responce.AccountDataResponse;

import java.util.List;

public interface FetchAllInterface {
    @GetMapping
    ResponseEntity<List<AccountDataResponse>> fetchAll();
}
