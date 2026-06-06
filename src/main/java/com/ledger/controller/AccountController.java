package com.ledger.controller;

import com.ledger.dto.AccountRequest;
import com.ledger.entity.Account;
import com.ledger.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping
    public Account createAccount(
            @RequestBody AccountRequest request) {

        return accountService.createAccount(request);
    }

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.getAllAccounts();
    }

    @GetMapping("/{id}")
    public Account getAccountById(
            @PathVariable Long id) {

        return accountService.getAccountById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteAccount(
            @PathVariable Long id) {

        accountService.deleteAccount(id);
        return "Account Deleted Successfully";
    }
    
    @GetMapping("/{id}/balance")
    public BigDecimal getBalance(
            @PathVariable Long id) {

        return accountService
                .getAccountById(id)
                .getBalance();
    }
}