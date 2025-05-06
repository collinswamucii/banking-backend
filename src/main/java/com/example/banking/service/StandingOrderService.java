package com.example.banking.service;

import com.example.banking.dto.StandingOrderDTO;
import com.example.banking.entity.Account;
import com.example.banking.entity.StandingOrder;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.StandingOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class StandingOrderService {

    private final StandingOrderRepository standingOrderRepository;
    private final AccountRepository accountRepository;

    public StandingOrder createStandingOrder(StandingOrderDTO standingOrderDTO) {
        Account sourceAccount = accountRepository.findById(standingOrderDTO.getSourceAccountId())
                .orElseThrow(() -> new RuntimeException("Source account not found"));
        Account destinationAccount = accountRepository.findById(standingOrderDTO.getDestinationAccountId())
                .orElseThrow(() -> new RuntimeException("Destination account not found"));

        StandingOrder standingOrder = StandingOrder.builder()
                .sourceAccount(sourceAccount)
                .destinationAccount(destinationAccount)
                .amount(standingOrderDTO.getAmount())
                .schedule(standingOrderDTO.getSchedule())
                .nextExecutionTime(LocalDateTime.now().plusMinutes(1))
                .build();
        return standingOrderRepository.save(standingOrder);
    }
}