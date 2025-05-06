package com.example.banking.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StandingOrderDTO {
    private Long sourceAccountId;
    private Long destinationAccountId;
    private Double amount;
    private String schedule;
}