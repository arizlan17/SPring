package org.example.springbootassignment.controller;

import org.example.springbootassignment.dto.transactionDto.CreateTransactionDto;
import org.example.springbootassignment.dto.transactionDto.TransactionHistoryRepositoryDto;
import org.example.springbootassignment.dto.transactionDto.TransactionSummaryDto;
import org.example.springbootassignment.enums.TransactionType;
import org.example.springbootassignment.model.Transaction;
import org.example.springbootassignment.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@CrossOrigin(origins = "*")
public class TransactionController {

    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("accountNumber/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountNumber(long accountNumber){
        return ResponseEntity.ok(transactionService.getTransactionByAccountNumber(accountNumber));
    }

    @GetMapping("amount/{amount}")
    public ResponseEntity<List<Transaction>> getTransactionsByAmount(double amount){
        return ResponseEntity.ok(transactionService.getTransactionByAmount(amount));
    }
    @GetMapping("transactionType/{transactionType}")
    public ResponseEntity< List<Transaction>> getTransactionsByTransactionType(TransactionType transactionType){
        return ResponseEntity.ok(transactionService.getTransactionByTransactionType(transactionType));
    }

    @PostMapping()
    public ResponseEntity<TransactionSummaryDto> saveTransaction(@RequestBody CreateTransactionDto createTransactionDto){
        return ResponseEntity.ok(transactionService.createTransaction(createTransactionDto));
    }



}
