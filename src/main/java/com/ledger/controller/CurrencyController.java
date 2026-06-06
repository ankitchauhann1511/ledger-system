package com.ledger.controller;

import com.ledger.entity.Currency;
import com.ledger.entity.ExchangeRate;
import com.ledger.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/currency")
@RequiredArgsConstructor
public class CurrencyController {

    private final CurrencyService currencyService;

    @PostMapping
    public Currency createCurrency(
            @RequestBody Currency currency) {

        return currencyService.saveCurrency(currency);
    }

    @PostMapping("/rate")
    public ExchangeRate saveRate(
            @RequestBody ExchangeRate exchangeRate) {

        return currencyService.saveExchangeRate(exchangeRate);
    }

    @GetMapping("/convert")
    public BigDecimal convert(
            @RequestParam BigDecimal amount,
            @RequestParam String from,
            @RequestParam String to) {

        return currencyService.convert(
                amount,
                from,
                to);
    }
}