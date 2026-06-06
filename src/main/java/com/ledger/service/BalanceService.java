package com.ledger.service;

import com.ledger.entity.Account;
import com.ledger.enums.AccountType;
import com.ledger.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final AccountRepository accountRepository;

    public void updateBalance(
            Account account,
            BigDecimal debit,
            BigDecimal credit) {

        BigDecimal balance = account.getBalance();

        if (balance == null) {
            balance = BigDecimal.ZERO;
        }

        AccountType type = account.getAccountType();

        switch (type) {

            case ASSET:
            case EXPENSE:
                balance = balance.add(debit).subtract(credit);
                break;

            case LIABILITY:
            case EQUITY:
            case REVENUE:
                balance = balance.add(credit).subtract(debit);
                break;
        }

        account.setBalance(balance);

        accountRepository.save(account);
    }
}