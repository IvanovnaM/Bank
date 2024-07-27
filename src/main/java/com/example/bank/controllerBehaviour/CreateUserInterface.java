package com.example.bank.controllerBehaviour;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.example.bank.DTO.UserDTO;
import com.example.bank.responce.UserResponse;

public interface CreateUserInterface {
    @PostMapping("/create")
    ResponseEntity<UserResponse> createUser(@RequestBody UserDTO userDTO);
}
