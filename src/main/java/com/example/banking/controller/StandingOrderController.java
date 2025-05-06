package com.example.banking.controller;

import com.example.banking.dto.StandingOrderDTO;
import com.example.banking.entity.StandingOrder;
import com.example.banking.service.StandingOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/standing-orders")
@RequiredArgsConstructor
public class StandingOrderController {

    private final StandingOrderService standingOrderService;

    @PostMapping
    public ResponseEntity<StandingOrder> createStandingOrder(@RequestBody StandingOrderDTO standingOrderDTO) {
        StandingOrder standingOrder = standingOrderService.createStandingOrder(standingOrderDTO);
        return new ResponseEntity<>(standingOrder, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStandingOrder(@PathVariable Long id) {
        standingOrderService.deleteStandingOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}