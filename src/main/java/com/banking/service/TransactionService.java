package com.banking.service;

import com.banking.dto.TransactionDto;


public interface TransactionService {

    void saveTransaction(TransactionDto transactionDto);
}
