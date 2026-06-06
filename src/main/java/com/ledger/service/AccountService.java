package com.ledger.service;

import com.ledger.dto.AccountRequest;
import com.ledger.entity.Account;
import com.ledger.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    public Account createAccount(AccountRequest request) {

        if (accountRepository.existsByAccountCode(
                request.getAccountCode())) {

            throw new RuntimeException(
                    "Account Code already exists");
        }

        Account account = new Account();

        account.setAccountCode(request.getAccountCode());
        account.setAccountName(request.getAccountName());
        account.setAccountType(request.getAccountType());

        account.setBalance(
                request.getBalance() == null
                        ? BigDecimal.ZERO
                        : request.getBalance());

        if (request.getParentAccountId() != null) {

            Account parentAccount =
                    accountRepository.findById(
                                    request.getParentAccountId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Parent Account Not Found"));

            account.setParentAccount(parentAccount);
        }

        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Account Not Found"));
    }

    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }
}