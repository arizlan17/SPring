package org.example.springbootassignment.controller;

import jakarta.validation.Valid;
import org.example.springbootassignment.dto.bankAccountDto.BankAccountSummaryDto;
import org.example.springbootassignment.dto.bankAccountDto.CreateBankAccountDto;
import org.example.springbootassignment.dto.bankAccountDto.CreatedBankAccountDto;
import org.example.springbootassignment.dto.depositeDto.DepositDto;
import org.example.springbootassignment.dto.withdrawalDto.WithdrawalDto;
import org.example.springbootassignment.service.BankAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bankAccount")
@CrossOrigin(origins = "*")

public class BankAccountController {

    private final BankAccountService bankAccountService;

    public BankAccountController(BankAccountService bankAccountService) {
        this.bankAccountService = bankAccountService;
    }


//    Create Bank Account
    @PostMapping()
    public ResponseEntity<CreatedBankAccountDto> createAccount(@Valid @RequestBody CreateBankAccountDto createBankAccountDto){
        CreatedBankAccountDto bankAccount = bankAccountService.createBankAccount(createBankAccountDto);
        return ResponseEntity.ok(bankAccount);
    }


//Get Bank Account by Account Number
    @GetMapping("/{accountNumber}")
    public ResponseEntity<CreatedBankAccountDto> getBankAccountByAccountNumber(@PathVariable long accountNumber){
        CreatedBankAccountDto bankAccountDto = bankAccountService.findAccountByAccountNumber(accountNumber);
    return ResponseEntity.ok(bankAccountDto);
    }

//    Get Account by Customer NIC Number
    @GetMapping("/nic/{nic}")
    public List<BankAccountSummaryDto> getAllBankAccounts(@PathVariable String nic){
        return bankAccountService.findAllBankAccount(nic);
    }


//    Deposite
    @PostMapping("{accountNumber}/deposite")
    public ResponseEntity<CreatedBankAccountDto> deposite(@PathVariable long accountNumber, @Valid @RequestBody DepositDto depositDto){
        CreatedBankAccountDto updatedBankAccount = bankAccountService.depositMoney(accountNumber, depositDto);
        return ResponseEntity.ok(updatedBankAccount);
    }

    //    Deposite
    @PostMapping("{accountNumber}/withdraw")
    public ResponseEntity<CreatedBankAccountDto> withdraw(@PathVariable long accountNumber, @Valid @RequestBody WithdrawalDto withdrawalDto){
        CreatedBankAccountDto updatedBankAccount = bankAccountService.withdrawMoney(accountNumber, withdrawalDto);
        return ResponseEntity.ok(updatedBankAccount);
    }
}
