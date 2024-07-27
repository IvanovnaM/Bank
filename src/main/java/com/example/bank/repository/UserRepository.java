package com.example.bank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    User findUserByEmail(String email);
    User findUserByAccount_AccountId(Long accountNumber);
}
