package com.example.banking.repository;

import com.example.banking.entity.StandingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface StandingOrderRepository extends JpaRepository<StandingOrder, Long> {
    List<StandingOrder> findByNextExecutionTimeBefore(LocalDateTime now);
}