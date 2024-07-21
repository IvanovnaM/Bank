package com.example.bank.repository;
import com.example.bank.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByBeneficiaryName(String beneficiaryName);

}
