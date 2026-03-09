package org.example.springbootassignment.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(name = "customer" ,indexes = @Index(name = "nic_number", columnList = "nic_number", unique = true))


public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long  customerId;

    @Column(nullable = false, unique = true)
    private  String  nicNumber;


    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String emailAddress;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToMany(fetch = FetchType.LAZY , cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "owner")
    @Builder.Default
    private List<BankAccount> accounts = new ArrayList<>();

    @Column(nullable = false)
    @Builder.Default
    private boolean isActive= true;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;



}
