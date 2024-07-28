package com.example.bank.service;

import com.example.bank.DTO.UserDTO;
import com.example.bank.model.Account;
import com.example.bank.model.User;
import com.example.bank.repository.UserRepository;
import com.example.bank.responce.AccountDataResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.OngoingStubbing;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static jdk.internal.org.objectweb.asm.util.CheckClassAdapter.verify;
import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class UserServeceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFetchAll() {
        User user1 = new User();
        user1.setName("John Doe");
        Account account1 = new Account();
        account1.setAccountId(1L);
        account1.setBalance(new BigDecimal(1000.0));
        user1.setAccount(account1);

        User user2 = new User();
        user2.setName("Jane Doe");
        Account account2 = new Account();
        account2.setAccountId(2L);
        account2.setBalance(new BigDecimal(2000.0));
        user2.setAccount(account2);

        when(userRepository.findAll()).thenReturn(Arrays.asList(user1, user2));

        List<AccountDataResponse> result = userService.fetchAll();

        assertNotNull(result);
        assertEquals(2, ((List<?>) result).size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals(1L, result.get(0).getAccountNumber());
        assertEquals(1000.0, result.get(0).getBalance());
        assertEquals("Jane Doe", result.get(1).getName());
        assertEquals(2L, result.get(1).getAccountNumber());
        assertEquals(2000.0, result.get(1).getBalance());
    }

    @Test
    public void testGetUserByEmail() {
        User user = new User();
        user.setEmail("user@example.com");
        OngoingStubbing<User> userOngoingStubbing = when(userRepository.findUserByEmail("user@example.com")).thenReturn(user);

        User result = userService.getUserByEmail("user@example.com");

        assertNotNull(result);
        assertEquals("user@example.com", result.getEmail());
    }

    @Test
    public void testGetUserByAccountNumber() {
        User user = new User();
        Account account = new Account();
        account.setAccountId(1L);
        user.setAccount(account);
        when(userRepository.findUserByAccount_AccountId(1L)).thenReturn(user);

        User result = userService.getUserByAccountNumber(1L);

        assertNotNull(result);
        assertEquals(1L, result.getAccount().getAccountId());
    }

    @Test
    public void testCreateUserAccount() {
        UserDTO userDTO = new UserDTO();
        userDTO.setAccountNumber(1L);
        userDTO.setEmail("user@example.com");
        User user = new User();
        user.setEmail("user@example.com");

        doNothing().when(accountService).createAccountForUser(userDTO.getAccountNumber());
        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUserAccount(userDTO);

        assertNotNull(result);
        assertEquals("user@example.com", result.getEmail());
        verify(accountService, times(1)).createAccountForUser(userDTO.getAccountNumber());
        verify(userRepository, times(1)).save(any(User.class));
    }


}
