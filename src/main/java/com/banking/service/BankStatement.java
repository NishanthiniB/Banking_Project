package com.banking.service;

import com.banking.entity.Transaction;
import com.banking.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@AllArgsConstructor
public class BankStatement {

private TransactionRepository repository;

public List<Transaction> generateStatement(String accountNumber, String startDate, String endDate){
    LocalDate start = LocalDate.parse(startDate, DateTimeFormatter.ISO_DATE);
    LocalDate end = LocalDate.parse(endDate, DateTimeFormatter.ISO_DATE);

    List<Transaction> transactionList = repository.findAll().stream().filter(transaction -> transaction.getAccountNumber().equals(accountNumber))
            .filter(transaction -> transaction.getCreatedAt().isEqual(start)).filter(transaction -> transaction.getModifiedAt().isEqual(end)).toList();

    return transactionList;

}


}
