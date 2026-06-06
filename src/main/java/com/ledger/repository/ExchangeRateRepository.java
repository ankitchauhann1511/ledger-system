package com.ledger.repository;

import com.ledger.entity.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExchangeRateRepository
        extends JpaRepository<ExchangeRate, Long> {

    Optional<ExchangeRate> findTopByFromCurrencyAndToCurrencyOrderByEffectiveDateDesc(
            String fromCurrency,
            String toCurrency
    );
}