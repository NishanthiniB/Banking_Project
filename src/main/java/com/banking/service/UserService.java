package com.banking.service;

import com.banking.dto.BankResponse;
import com.banking.dto.CreditDebitRequest;
import com.banking.dto.EnquiryRequest;
import com.banking.dto.UserRequest;

public interface UserService {

    BankResponse createAccount(UserRequest userRequest);
    BankResponse balanceEnquiry(EnquiryRequest request);
    String nameEnquiry(EnquiryRequest request);

    BankResponse creditAccount(CreditDebitRequest request);
    BankResponse debitAccount(CreditDebitRequest request);


}
