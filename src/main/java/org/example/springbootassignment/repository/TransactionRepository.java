package org.example.springbootassignment.repository;

import org.example.springbootassignment.dto.transactionDto.CreateTransactionDto;
import org.example.springbootassignment.dto.transactionDto.TransactionDto;
import org.example.springbootassignment.dto.transactionDto.TransactionSummaryDto;
import org.example.springbootassignment.enums.TransactionType;
import org.example.springbootassignment.model.Transaction;
import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,String> {

    Transaction save(Transaction transaction);

    List<Transaction> findAllByBankAccountAccountNumber(long accountNumber);
    List<Transaction> findAllByTransactionAmount(double transactionAmount);
    List<Transaction> findAllByTransactionDate(LocalDateTime transactionDate);
    List<Transaction> findAllByTransactionType(TransactionType transactionType);
    List<Transaction> findAllByTransactionDateAndTransactionTypeAndTransactionAmount(LocalDateTime transactionDate, TransactionType transactionType, double transactionAmount);
}
