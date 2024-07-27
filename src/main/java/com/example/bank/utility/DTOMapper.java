package com.example.bank.utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.example.bank.DTO.UserDTO;
import com.example.bank.model.User;
import com.example.bank.repository.AccountRepository;


@Component
public class DTOMapper {
    public static AccountRepository accountRepository;

    @Autowired
    public DTOMapper(AccountRepository accountRepository) {
        DTOMapper.accountRepository = accountRepository;
    }

    public static UserDTO getUserDTO(User user){

        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setPin(user.getPIN());
        userDTO.setEmail(userDTO.getEmail());
        userDTO.setAccountNumber(user.getAccount().getAccountId());

        return userDTO;
    }

    //Делает из ДТО ---> USER
    public static User getUser(UserDTO userDTO){

        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setPIN(userDTO.getPin());
        user.setName(userDTO.getName());
        user.setAccount(accountRepository.findAccountByAccountId(userDTO.getAccountNumber()));

        return user;
    }
}
