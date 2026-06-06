package com.ledger.controller;

import com.ledger.dto.BalanceSheetDTO;
import com.ledger.dto.ProfitLossDTO;
import com.ledger.dto.TrialBalanceDTO;
import com.ledger.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/trial-balance")
    public List<TrialBalanceDTO> trialBalance() {
        return reportService.generateTrialBalance();
    }

    @GetMapping("/profit-loss")
    public ProfitLossDTO profitLoss() {
        return reportService.generateProfitLoss();
    }

    @GetMapping("/balance-sheet")
    public BalanceSheetDTO balanceSheet() {
        return reportService.generateBalanceSheet();
    }
}