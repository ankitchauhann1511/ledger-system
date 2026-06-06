package com.ledger.service;

import com.ledger.dto.JournalEntryRequest;
import com.ledger.dto.JournalLineRequest;
import com.ledger.entity.Account;
import com.ledger.entity.JournalEntry;
import com.ledger.entity.JournalLine;
import com.ledger.repository.AccountRepository;
import com.ledger.repository.JournalEntryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class JournalEntryService {

    private final JournalEntryRepository journalEntryRepository;
    private final AccountRepository accountRepository;
    private final AuditService auditService;
    private final BalanceService balanceService;

    public JournalEntry createEntry(JournalEntryRequest request) {

        BigDecimal totalDebit = BigDecimal.ZERO;
        BigDecimal totalCredit = BigDecimal.ZERO;

        // Validate Total Debit = Total Credit
        for (JournalLineRequest line : request.getJournalLines()) {

            BigDecimal debit =
                    line.getDebitAmount() == null
                            ? BigDecimal.ZERO
                            : line.getDebitAmount();

            BigDecimal credit =
                    line.getCreditAmount() == null
                            ? BigDecimal.ZERO
                            : line.getCreditAmount();

            totalDebit = totalDebit.add(debit);
            totalCredit = totalCredit.add(credit);
        }

        if (totalDebit.compareTo(totalCredit) != 0) {
            throw new RuntimeException(
                    "Invalid Journal Entry: Total Debit must equal Total Credit");
        }

        JournalEntry journalEntry = new JournalEntry();
        journalEntry.setReferenceId(request.getReferenceId());
        journalEntry.setDescription(request.getDescription());

        List<JournalLine> journalLines = new ArrayList<>();

        // Save Journal Lines + Update Balances
        for (JournalLineRequest lineRequest : request.getJournalLines()) {

            Account account =
                    accountRepository.findById(
                                    lineRequest.getAccountId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Account not found with ID: "
                                                    + lineRequest.getAccountId()));

            BigDecimal debit =
                    lineRequest.getDebitAmount() == null
                            ? BigDecimal.ZERO
                            : lineRequest.getDebitAmount();

            BigDecimal credit =
                    lineRequest.getCreditAmount() == null
                            ? BigDecimal.ZERO
                            : lineRequest.getCreditAmount();

            // Validation: both debit and credit not allowed
            if (debit.compareTo(BigDecimal.ZERO) > 0
                    && credit.compareTo(BigDecimal.ZERO) > 0) {

                throw new RuntimeException(
                        "A journal line cannot contain both Debit and Credit amounts");
            }

            JournalLine journalLine = new JournalLine();

            journalLine.setJournalEntry(journalEntry);
            journalLine.setAccount(account);
            journalLine.setDebitAmount(debit);
            journalLine.setCreditAmount(credit);

            journalLines.add(journalLine);

            // Update Account Balance
            balanceService.updateBalance(
                    account,
                    debit,
                    credit
            );
        }

        journalEntry.setJournalLines(journalLines);

        // Save Journal Entry
        JournalEntry savedEntry =
                journalEntryRepository.save(journalEntry);

        // Audit Log
        auditService.log(
                "CREATE",
                "JOURNAL_ENTRY",
                savedEntry.getId(),
                "Journal Entry Created : "
                        + savedEntry.getReferenceId()
        );

        return savedEntry;
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepository.findAll();
    }

    public JournalEntry getEntryById(Long id) {
        return journalEntryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Journal Entry Not Found"));
    }
}