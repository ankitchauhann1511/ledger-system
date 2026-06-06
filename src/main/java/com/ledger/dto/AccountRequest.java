package com.ledger.dto;

import com.ledger.enums.AccountType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AccountRequest {

    private String accountCode;

    private String accountName;

    private AccountType accountType;

    private BigDecimal balance;

    private Long parentAccountId;
}