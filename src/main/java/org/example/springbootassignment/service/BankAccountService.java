package org.example.springbootassignment.service;

import org.example.springbootassignment.dto.BankAccountDto;
import org.example.springbootassignment.dto.CreatedBankAccountDto;
import org.example.springbootassignment.dto.CreateBankAccountDto;
import org.example.springbootassignment.model.BankAccount;
import org.example.springbootassignment.model.Customer;
import org.example.springbootassignment.repository.BankAccountRepository;
import org.example.springbootassignment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BankAccountService {
    private final CustomerRepository customerRepository;
    private final BankAccountRepository bankAccountRepository;

    @Autowired
    public BankAccountService(CustomerRepository customerRepository, BankAccountRepository bankAccountRepository) {
        this.customerRepository = customerRepository;
        this.bankAccountRepository = bankAccountRepository;
    }


    public CreatedBankAccountDto createBankAccount(CreateBankAccountDto createBankAccountDto){
        Customer customer = customerRepository.findByNicNumber(createBankAccountDto.nic()).orElseThrow(() -> new RuntimeException("Customer with NIC: " + createBankAccountDto.nic() + " not found"));
        BankAccount bankAccount = BankAccount.builder()
                .owner(customer)
                .accountBalance(createBankAccountDto.initialDeposit())
                .accountType(createBankAccountDto.accountType())
                .build();

        customer.getAccounts().add(bankAccount);
        customerRepository.save(customer);
        return CreatedBankAccountDto.from(bankAccountRepository.save(bankAccount));
    }


    public BankAccountDto findAccountByAccountNumber(long accountNumber){
        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank account with account number: " + accountNumber + " not found"));
    return BankAccountDto.from(bankAccount);
    }


    public List<BankAccountDto> findAllBankAccount(String  nicNumber){
        List<BankAccount> bankAccountList = bankAccountRepository.findAllByOwnerNicNumber(nicNumber);
        return bankAccountList.stream().map(BankAccountDto::from).toList();
    }

    public void deleteAccount (long accountNumber){
        BankAccount deletedBankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Bank account with account number: " + accountNumber + " not found"));
        deletedBankAccount.setActive(false);
        bankAccountRepository.save(deletedBankAccount);
    }


}
