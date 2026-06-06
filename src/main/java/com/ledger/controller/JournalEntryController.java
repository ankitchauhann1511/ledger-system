package com.ledger.controller;

import com.ledger.dto.JournalEntryRequest;
import com.ledger.entity.JournalEntry;
import com.ledger.service.JournalEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/journal")
@RequiredArgsConstructor
public class JournalEntryController {

    private final JournalEntryService journalEntryService;

    @PostMapping
    public JournalEntry createJournalEntry(
            @RequestBody JournalEntryRequest request) {

        return journalEntryService.createEntry(request);
    }
}