package com.ledger.dto;

import lombok.Data;

import java.util.List;

@Data
public class JournalEntryRequest {

    private String referenceId;

    private String description;

    private List<JournalLineRequest> journalLines;
}