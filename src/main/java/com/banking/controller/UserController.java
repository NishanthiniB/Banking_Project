package com.banking.controller;

import com.banking.dto.*;
import com.banking.service.UserService;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Tag(name="User Management APIs")
public class UserController {

    @Autowired
    UserService service;

    @Operation(
            summary="Create New Account",
            description = "Creating a new user and assigning an account ID"
    )
    @ApiResponse(
            responseCode = "201",
            description = "Https Status 201 CREATED"
    )
    @PostMapping("/create")
    public BankResponse createAccount(@RequestBody UserRequest  userRequest){
        return service.createAccount(userRequest);
    }
    @Operation(
            summary="Balance Enquiry",
            description = "Check user balance"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Https Status 200 SUCCESS"
    )
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

    @PostMapping("/transfer")
    public BankResponse transferAccount(@RequestBody TransferRequest request){
        return service.transfer(request);
    }
}
