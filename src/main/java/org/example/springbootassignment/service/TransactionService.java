package org.example.springbootassignment.service;


import org.example.springbootassignment.dto.transactionDto.CreateTransactionDto;
import org.example.springbootassignment.dto.transactionDto.TransactionSummaryDto;
import org.example.springbootassignment.enums.TransactionType;
import org.example.springbootassignment.model.BankAccount;
import org.example.springbootassignment.model.Transaction;
import org.example.springbootassignment.repository.BankAccountRepository;
import org.example.springbootassignment.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionService {

    public final TransactionRepository transactionRepository;
    public final BankAccountRepository bankAccountRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, BankAccountRepository bankAccountRepository) {
        this.transactionRepository = transactionRepository;
        this.bankAccountRepository = bankAccountRepository;
    }


    public List<Transaction> getTransactionByAccountNumber(long accountNumber){
        return transactionRepository.findAllByBankAccountAccountNumber(accountNumber);
    }


    public  List<Transaction> getTransactionByAmount(double amount){
        return transactionRepository.findAllByTransactionAmount(amount);
    }

    public List<Transaction> getTransactionByTransactionType(TransactionType transactionType){
        return transactionRepository.findAllByTransactionType(transactionType);
    }

    public TransactionSummaryDto createTransaction(CreateTransactionDto createTransactionDto){
        BankAccount account = bankAccountRepository.findByAccountNumber(createTransactionDto.accountNumber()).orElseThrow(() -> new RuntimeException("Account with number: " + createTransactionDto.accountNumber() + " not found"));
        Transaction transaction = Transaction.builder()
                .bankAccount(account)
                .transactionAmount(createTransactionDto.transactionAmount())
                .transactionType(createTransactionDto.transactionType())
                .description(createTransactionDto.description())
                .transactionDate(LocalDateTime.now())
                .build();

        account.getTransactionHistory().add(transaction);



        return TransactionSummaryDto.from(transactionRepository.save(transaction));
    }

    public List<Transaction> getAllTransactions(){
        return transactionRepository.findAll();
    }

    public Transaction getTransactionById(String transactionId) {
        return transactionRepository.findById(transactionId).orElseThrow(() -> new RuntimeException("Transaction with ID: " + transactionId + " not found"));
    }

    public void deleteTransaction(String transactionId) {
        Transaction transaction = getTransactionById(transactionId);
        transactionRepository.delete(transaction);
    }

    public List<Transaction> getTransactionsByDate(String date) {
        return transactionRepository.findAllByTransactionDate(java.time.LocalDateTime.parse(date));
    }

    public List<Transaction> getTransactionsByDateByTransactionTypeByAmount(LocalDateTime transactionDate, TransactionType transactionType, double transactionAmount) {
        return transactionRepository.findAllByTransactionDateAndTransactionTypeAndTransactionAmount(transactionDate, transactionType, transactionAmount);
    }


}
