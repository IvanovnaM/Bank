package com.example.bank.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.bank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {
    Account findAccountByAccountId(Long id);
}
