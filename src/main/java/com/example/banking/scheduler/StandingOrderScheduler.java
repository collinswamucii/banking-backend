package com.example.banking.scheduler;

import com.example.banking.entity.StandingOrder;
import com.example.banking.repository.StandingOrderRepository;
import com.example.banking.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StandingOrderScheduler {

    private final StandingOrderRepository standingOrderRepository;
    private final TransactionService transactionService;

    @Scheduled(cron = "${spring.scheduler.standing-order.cron}")
    public void processStandingOrders() {
        List<StandingOrder> standingOrders = standingOrderRepository.findByNextExecutionTimeBefore(LocalDateTime.now());
        for (StandingOrder order : standingOrders) {
            try {
                transactionService.executeTransaction(
                        order.getSourceAccount(),
                        order.getDestinationAccount(),
                        order.getAmount()
                );
                order.setNextExecutionTime(calculateNextExecutionTime(order));
                standingOrderRepository.save(order);
            } catch (Exception e) {
                System.err.println("Failed to process standing order " + order.getId() + ": " + e.getMessage());
            }
        }
    }

    private LocalDateTime calculateNextExecutionTime(StandingOrder order) {
        return LocalDateTime.now().plusMinutes(1); // Simplified for testing
    }
}