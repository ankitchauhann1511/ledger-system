package com.ledger.service;

import com.ledger.entity.Currency;
import com.ledger.entity.ExchangeRate;
import com.ledger.repository.CurrencyRepository;
import com.ledger.repository.ExchangeRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;
    private final ExchangeRateRepository exchangeRateRepository;

    public Currency saveCurrency(Currency currency) {
        return currencyRepository.save(currency);
    }

    public ExchangeRate saveExchangeRate(
            ExchangeRate exchangeRate) {

        return exchangeRateRepository.save(exchangeRate);
    }

    public BigDecimal convert(
            BigDecimal amount,
            String fromCurrency,
            String toCurrency) {

        ExchangeRate rate =
                exchangeRateRepository
                        .findTopByFromCurrencyAndToCurrencyOrderByEffectiveDateDesc(
                                fromCurrency,
                                toCurrency)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Exchange Rate Not Found"));

        return amount.multiply(rate.getRate());
    }
}