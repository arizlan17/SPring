package org.example.springbootassignment.service;

import org.example.springbootassignment.dto.bankAccountDto.BankAccountSummaryDto;
import org.example.springbootassignment.dto.bankAccountDto.CreatedBankAccountDto;
import org.example.springbootassignment.dto.bankAccountDto.CreateBankAccountDto;
import org.example.springbootassignment.dto.depositeDto.DepositDto;
import org.example.springbootassignment.dto.withdrawalDto.WithdrawalDto;
import org.example.springbootassignment.enums.TransactionType;
import org.example.springbootassignment.model.BankAccount;
import org.example.springbootassignment.model.Customer;
import org.example.springbootassignment.model.Transaction;
import org.example.springbootassignment.repository.BankAccountRepository;
import org.example.springbootassignment.repository.CustomerRepository;
import org.example.springbootassignment.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;
    private final TransactionRepository transactionRepository;

    @Autowired
    public BankAccountService(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository, TransactionRepository transactionRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.transactionRepository = transactionRepository;
    }


    public CreatedBankAccountDto createBankAccount(CreateBankAccountDto createBankAccountDto){
        Customer customer = customerRepository.findByNicNumber(createBankAccountDto.nic()).orElseThrow(() -> new RuntimeException("Customer with NIC: " + createBankAccountDto.nic() + " not found"));
        BankAccount bankAccount = BankAccount.builder()
                .owner(customer)
                .accountBalance(createBankAccountDto.initialDeposit())
                .accountType(createBankAccountDto.accountType())
                .build();

        customer.getAccounts().add(bankAccount);
        return CreatedBankAccountDto.from(bankAccountRepository.save(bankAccount));
    }


    public CreatedBankAccountDto findAccountByAccountNumber(long accountNumber){
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank account with account number: " + accountNumber + " not found"));
    return CreatedBankAccountDto.from(bankAccount);
    }


    public List<BankAccountSummaryDto> findAllBankAccount(String  nicNumber){
        List<BankAccount> bankAccountList = bankAccountRepository.findAllByOwnerNicNumber(nicNumber);
        return bankAccountList.stream().map(BankAccountSummaryDto::from).toList();
    }

    public void deleteAccount (long accountNumber){
        BankAccount deletedBankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank account with account number: " + accountNumber + " not found"));
        deletedBankAccount.setActive(false);
        bankAccountRepository.save(deletedBankAccount);
    }

    public CreatedBankAccountDto depositMoney(long accountNumber, DepositDto depositDto){
        if (depositDto.amount() <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero");
        }
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank account with account number: " + accountNumber + " not found"));

//        Updaate Amount
        bankAccount.setAccountBalance(bankAccount.getAccountBalance() + depositDto.amount());

        Transaction transaction = Transaction.builder()
                .transactionAmount(depositDto.amount())
                .transactionType(TransactionType.CREDIT)
                .bankAccount(bankAccount)
                .description("Deposit of " + depositDto.amount())
                .build();


        bankAccount.getTransactionHistory().add(transaction);
        transactionRepository.save(transaction);

        BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);
        return CreatedBankAccountDto.from(updatedBankAccount);
    }


    public CreatedBankAccountDto withdrawMoney(long accountNumber, WithdrawalDto withdrawalDto) {
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank account with account number: " + accountNumber + " not found"));

        if (withdrawalDto.amount() <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero");
        }
        if (withdrawalDto.amount() > bankAccount.getAccountBalance()) {
            throw new IllegalArgumentException("Withdrawal amount exceeds the Account Balance");
        }

        bankAccount.setAccountBalance(bankAccount.getAccountBalance() - withdrawalDto.amount());

        Transaction transaction = Transaction.builder()
                .transactionAmount(withdrawalDto.amount())
                .transactionType(TransactionType.DEBIT)
                .bankAccount(bankAccount)
                .description("Withdrawal of " + withdrawalDto.amount())
                .build();

        bankAccount.getTransactionHistory().add(transaction);
        transactionRepository.save(transaction);

        BankAccount updatedBankAccount = bankAccountRepository.save(bankAccount);
        return CreatedBankAccountDto.from(updatedBankAccount);
    }




}
