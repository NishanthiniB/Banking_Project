package com.banking.service;

import com.banking.dto.TransactionDto;
import com.banking.entity.Transaction;
import com.banking.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

  @Autowired
    TransactionRepository repo;

    @Override
    public void saveTransaction(TransactionDto transactionDto) {
        Transaction transaction = Transaction.builder()

                .transactionType(transactionDto.getTransactionType())
                .accountNumber(transactionDto.getAccountNumber())
                .amount(transactionDto.getAmount())
                .status("SUCCESS")

                .build();
        repo.save(transaction);
        System.out.println("Transaction Saved Successfully");
    }
}
