package org.example.springbootassignment.model;

import jakarta.persistence.*;
import org.example.springbootassignment.enums.TransactionType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionID;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private  LocalDateTime transactionDate;
    private double transactionAmount;
    private TransactionType transactionType;

    @JoinColumn(name = "account_number", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    public Transactions(double transactionAmount, TransactionType transactionType) {
        this.transactionAmount = transactionAmount;
        this.transactionType = transactionType;

    }
    public Transactions() {

    }

    // Getter methods
    public String getTransactionID() {
        return transactionID;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    @Override
    public String toString() {
        return "Transaction ID: " + transactionID +
                " | Date: " + transactionDate +
                " | Amount: " + transactionAmount +
                " | Type: " + transactionType;
    }
}
