package com.example.banking.service;

import com.example.banking.dto.AccountDTO;
import com.example.banking.entity.Account;
import com.example.banking.entity.Customer;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public Account createAccount(AccountDTO accountDTO) {
        Customer customer = customerRepository.findById(accountDTO.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Account account = Account.builder()
                .customer(customer)
                .accountType(accountDTO.getAccountType())
                .balance(accountDTO.getBalance() != null ? accountDTO.getBalance() : 0.0)
                .build();
        return accountRepository.save(account);
    }
}