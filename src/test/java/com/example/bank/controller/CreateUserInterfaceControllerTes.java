package com.example.bank.controller;

import com.example.bank.DTO.UserDTO;
import com.example.bank.model.Account;
import com.example.bank.model.User;
import com.example.bank.model.UserLogIn;
import com.example.bank.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CreateUserInterfaceControllerTes {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private CreateUserInterfaceController createUserInterfaceController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createUserInterfaceController).build();
    }

    @Test
    public void testLoginSuccess() throws Exception {
        UserLogIn userLogIn = new UserLogIn("user@example.com", 0000);
        User user = new User();
        user.setName("John Doe");
        Account account = new Account();
        account.setBalance(new BigDecimal(1000.0));
        user.setAccount(account);

        when(userService.getUserByEmail(userLogIn.getEmail())).thenReturn(user);

        mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(new ObjectMapper().writeValueAsString(userLogIn)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.balance").value(1000.0))
                .andExpect(jsonPath("$.message").value("Авторизация прошла успешно"));
    }

    @Test
    public void testLoginUserNotFound() throws Exception {
        UserLogIn userLogIn = new UserLogIn("user@example.com", "password");

        when(userService.getUserByEmail(userLogIn.getEmail())).thenReturn(null);

        mockMvc.perform(post("/api/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(new ObjectMapper().writeValueAsString(userLogIn)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Нет такого аккаунта попбробуйте зарегистрироваться"));
    }

    @Test
    public void testCreateUserSuccess() throws Exception {
        UserDTO userDTO = new UserDTO("John Doe", "user@example.com", "password");
        Account account = new Account();
        account.setAccountId(1L);
        account.setBalance(10000.0);
        User user = new User();
        user.setName("John Doe");
        user.setEmail("user@example.com");
        user.setAccount(account);

        when(userService.getUserByEmail(userDTO.getEmail())).thenReturn(null);
        when(userService.createUserAccount(any(UserDTO.class))).thenReturn(user);

        mockMvc.perform(post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.success").value(true))
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("user@example.com"))
                .andExpect(jsonPath("$.accountId").value(1))
                .andExpect(jsonPath("$.message").value("Аккаунт успешно создан"))
                .andExpect(jsonPath("$.balance").value(10000.0));
    }

    @Test
    public void testCreateUserConflict() throws Exception {
        UserDTO userDTO = new UserDTO("John Doe", "user@example.com", "password");

        when(userService.getUserByEmail(userDTO.getEmail())).thenReturn(new User());

        mockMvc.perform(post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Такой пользователь существует"));
    }

    @Test
    public void testCreateUserInternalServerError() throws Exception {
        UserDTO userDTO = new UserDTO("John Doe", "user@example.com", "password");

        when(userService.getUserByEmail(userDTO.getEmail())).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .contentType(new ObjectMapper().writeValueAsString(userDTO)))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.success").value(false))
                .andExpect(jsonPath("$.message").value("Произошла ошибка при создании аккаунта"));
    }
}
