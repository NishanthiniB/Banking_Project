package com.banking.controller;

import com.banking.dto.BankResponse;
import com.banking.dto.CreditDebitRequest;
import com.banking.dto.EnquiryRequest;
import com.banking.dto.UserRequest;
import com.banking.service.UserService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    UserService service;


    @PostMapping("/create")
    public BankResponse createAccount(@RequestBody UserRequest  userRequest){
        return service.createAccount(userRequest);
    }

    @GetMapping("/balanceEnquiry")
    public BankResponse balanceEnquiry(@RequestBody EnquiryRequest request){
        return service.balanceEnquiry(request);
    }

    @GetMapping("/nameEnquiry")
    public String nameEnquiry(@RequestBody EnquiryRequest request){
        return service.nameEnquiry(request);
    }

    @PostMapping("/credit")
    public BankResponse creditAccount(@RequestBody CreditDebitRequest request){
        return service.creditAccount(request);
    }

    @PostMapping("/debit")
    public BankResponse debitAccount(@RequestBody CreditDebitRequest request){
        return service.debitAccount(request);
    }
}
