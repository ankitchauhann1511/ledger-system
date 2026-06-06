package com.ledger.service;

import com.ledger.dto.BalanceSheetDTO;
import com.ledger.dto.ProfitLossDTO;
import com.ledger.dto.TrialBalanceDTO;
import com.ledger.entity.Account;
import com.ledger.enums.AccountType;
import com.ledger.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final AccountRepository accountRepository;

    public List<TrialBalanceDTO> generateTrialBalance() {

        List<TrialBalanceDTO> report = new ArrayList<>();

        for (Account account : accountRepository.findAll()) {

            BigDecimal debit = BigDecimal.ZERO;
            BigDecimal credit = BigDecimal.ZERO;

            if (account.getAccountType() == AccountType.ASSET
                    || account.getAccountType() == AccountType.EXPENSE) {

                debit = account.getBalance();
            } else {

                credit = account.getBalance();
            }

            report.add(
                    new TrialBalanceDTO(
                            account.getAccountCode(),
                            account.getAccountName(),
                            debit,
                            credit
                    )
            );
        }

        return report;
    }

    public ProfitLossDTO generateProfitLoss() {

        BigDecimal revenue = BigDecimal.ZERO;
        BigDecimal expense = BigDecimal.ZERO;

        for (Account account : accountRepository.findAll()) {

            if (account.getAccountType() == AccountType.REVENUE) {
                revenue = revenue.add(account.getBalance());
            }

            if (account.getAccountType() == AccountType.EXPENSE) {
                expense = expense.add(account.getBalance());
            }
        }

        return new ProfitLossDTO(
                revenue,
                expense,
                revenue.subtract(expense)
        );
    }

    public BalanceSheetDTO generateBalanceSheet() {

        BigDecimal assets = BigDecimal.ZERO;
        BigDecimal liabilities = BigDecimal.ZERO;
        BigDecimal equity = BigDecimal.ZERO;

        for (Account account : accountRepository.findAll()) {

            switch (account.getAccountType()) {

                case ASSET:
                    assets = assets.add(account.getBalance());
                    break;

                case LIABILITY:
                    liabilities = liabilities.add(account.getBalance());
                    break;

                case EQUITY:
                    equity = equity.add(account.getBalance());
                    break;
            }
        }

        return new BalanceSheetDTO(
                assets,
                liabilities,
                equity
        );
    }
}