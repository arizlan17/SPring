package org.example.springbootassignment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootassignment.enums.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)

public class BankAccount {
    @Id
    private Long accountNumber;

    @Column(nullable = false)
    private double accountBalance;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id",nullable = false)
    @JsonBackReference
    private Customer owner;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true,mappedBy = "bankAccount")
    @Builder.Default
    private List<Transaction> transactionHistory = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    boolean isActive= true;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Deposit amount must be greater than 0.");
            return false;
        }

        this.accountBalance += amount;
        Transaction transaction = new Transaction( amount, TransactionType.CREDIT);
        transactionHistory.add(transaction);

        System.out.println("Successfully deposited: " + amount);
        System.out.println("Current Balance:" + accountBalance);
        return true;
    }


    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be greater than 0.");
            return false;
        }

        if (amount > accountBalance) {
            System.out.println("Insufficient funds. Current balance: " + accountBalance);
            return false;
        }

        this.accountBalance -= amount;
        Transaction transaction = new Transaction( amount, TransactionType.DEBIT);
        transactionHistory.add(transaction);

        System.out.println("Successfully withdrawn: " + amount);
        System.out.println("Current Balance: " + accountBalance);
        return true;
    }


    public void viewTransactionHistory() {
        System.out.println("\n" + "-".repeat(100));
        System.out.println("TRANSACTION HISTORY - " + accountNumber);
        System.out.println("Holder: " + owner.getCustomerName());
        System.out.println("-".repeat(100));

        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions found for this account.");
        } else {
            System.out.println(String.format("%-15s | %-25s | %-15s | %-10s", "Transaction ID", "Date & Time", "Amount", "Type"));
            System.out.println("-".repeat(100));

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            for (Transaction transaction : transactionHistory) {
                System.out.println(String.format(
                        "%-15s | %-25s | $%-14.2f | %-10s",
                        transaction.getTransactionId(),
                        transaction.getTransactionDate().format(formatter),
                        transaction.getTransactionAmount(),
                        transaction.getTransactionType()
                ));
            }
        }
        System.out.println("-".repeat(100));
        System.out.println("Current Account Balance:" + String.format("%.2f", accountBalance));
        System.out.println("-".repeat(100) + "\n");
    }
    @PrePersist
    protected void onCreate() {
        // Generate a random 9-digit number if it doesn't already have one
        if (this.accountNumber == null) {
            long min = 100000000L; // Smallest 9-digit number
            long max = 999999999L; // Largest 9-digit number
            this.accountNumber = min + (long) (Math.random() * (max - min + 1));
        }}

    @Override
    public String toString() {
        return "BankAccount{" +
                "accountNumber=" + accountNumber +
                ", holderName='" + owner.getCustomerName() + '\'' +
                ", accountBalance=" + accountBalance +
                ", accountType=" + accountType +
                '}';
    }
}
