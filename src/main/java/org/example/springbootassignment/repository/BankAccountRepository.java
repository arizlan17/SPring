package org.example.springbootassignment.repository;

import org.example.springbootassignment.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    BankAccount save(BankAccount bankAccount);
    Optional<BankAccount> findByAccountNumber(long accountNumber);
    List<BankAccount> findAllByOwnerNicNumber(String nicNumber);

}
