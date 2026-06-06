package com.ledger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ledger.enums.AccountType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String accountCode;

    @Column(nullable = false)
    private String accountName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AccountType accountType;

    private BigDecimal balance;

    @ManyToOne
    @JoinColumn(name = "parent_account_id")
    private Account parentAccount;

    @OneToMany(mappedBy = "parentAccount")
    @JsonIgnore
    private List<Account> childAccounts;
}