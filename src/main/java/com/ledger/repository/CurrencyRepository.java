package com.ledger.repository;

import com.ledger.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository
        extends JpaRepository<Currency, Long> {
}