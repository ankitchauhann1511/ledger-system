package com.ledger.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class JournalLineRequest {

    private Long accountId;

    private BigDecimal debitAmount;

    private BigDecimal creditAmount;
}