package com.ledger.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TrialBalanceDTO {

    private String accountCode;
    private String accountName;
    private BigDecimal debit;
    private BigDecimal credit;
}