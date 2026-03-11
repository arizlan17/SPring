package org.example.springbootassignment.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.springbootassignment.enums.TransactionType;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String transactionId;

    @CreationTimestamp
    @Column(updatable = false, nullable = false)
    private  LocalDateTime transactionDate;


    @Column(nullable = false)
    private double transactionAmount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType;

    @Column(nullable = false)
    private String description;

    @JoinColumn(name = "account_number", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    public Transaction(double amount, TransactionType transactionType) {
    }


    // Getter methods


    @Override
    public String toString() {
        return "Transaction ID: " + transactionId +
                " | Date: " + transactionDate +
                " | Amount: " + transactionAmount +
                " | Type: " + transactionType;
    }
}
