package com.banking.service;

import com.banking.dto.*;
import com.banking.entity.User;
import com.banking.repository.UserRepository;
import com.banking.utils.AccountUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.accessibility.AccessibleComponent;
import java.math.BigDecimal;
import java.math.BigInteger;

@Service
public class UserServiceImpl implements UserService {


    @Autowired
    UserRepository repo;

    @Autowired
    EmailService emailService;
    @Override
    public BankResponse createAccount(UserRequest userRequest) {
        /**
         * Creating an account saving a new user into the db
         */


        if(repo.existsByEmail(userRequest.getEmail())){
            BankResponse response = BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_EXISTS_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_EXISTS_MESSAGE)
                    .accountInfo(null)
                    .build();
            return response;
        }

        User newUser = User.builder()

                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .gender(userRequest.getGender())
                .address(userRequest.getAddress())
                .phnNumber(userRequest.getPhnNumber())
                .stateOfOrigin(userRequest.getStateOfOrigin())
                .accountNumber(AccountUtils.generateAccountNumber())
                .email(userRequest.getEmail())
                .status("ACTIVE")
                .accountBalance(BigDecimal.ZERO)
                .build();
        User saveUser = repo.save(newUser);
        EmailDetails details = EmailDetails.builder()
                .recipient(saveUser.getEmail())
                .subject("ACCOUNT CREATION")
                .messageBody("CONGRATULATIONS! YOUR ACCOUNT HAS BEEN SUCCESSFULLY CREATED.\n YOUR ACCOUNT DETAILS:\n ACCOUNT NAME: " +saveUser.getFirstName() + " " + saveUser.getLastName() + "\nACCOUNT NUMBER: " + saveUser.getAccountNumber())
                .build();
        emailService.sendEmailAlert(details);

        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_CREATION_SUCCESS)
                .responseMessage(AccountUtils.ACCOUNT_CREATION_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(saveUser.getAccountBalance())
                        .accountNumber(saveUser.getAccountNumber())
                        .accountName(saveUser.getFirstName()+" " + saveUser.getLastName() + " ")
                        .build())
                .build();


    }

    @Override
    public BankResponse balanceEnquiry(EnquiryRequest request) {
        boolean isAccountExist = repo.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        User foundUser = repo.findByAccountNumber(request.getAccountNumber());
        return BankResponse.builder()
                .responseCode(AccountUtils.ACCOUNT_FOUND_CODE)
                .responseMessage(AccountUtils.ACCOUNT_FOUND_MESSAGE)
                .accountInfo(AccountInfo.builder()
                        .accountBalance(foundUser.getAccountBalance())
                        .accountNumber(request.getAccountNumber())
                        .accountName(foundUser.getFirstName()+ " " + foundUser.getLastName())
                        .build())
                .build();
    }

    @Override
    public String nameEnquiry(EnquiryRequest request) {
        boolean isAccountExist = repo.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            return AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE;
        }
      User foundUser = repo.findByAccountNumber(request.getAccountNumber());
        return foundUser.getFirstName() + " " + foundUser.getLastName();
    }

    @Override
    public BankResponse creditAccount(CreditDebitRequest request) {
        boolean isAccountExist = repo.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User userCredit = repo.findByAccountNumber(request.getAccountNumber());
        userCredit.setAccountBalance(userCredit.getAccountBalance().add(request.getAmount()));

        repo.save(userCredit);



       return BankResponse.builder()
               .responseCode(AccountUtils.ACCOUNT_CREDITED_SUCCESS)
               .responseMessage(AccountUtils.ACCOUNT_CREDITED_SUCCESS_MESSAGE)
               .accountInfo(AccountInfo.builder()

                       .accountName(userCredit.getFirstName() + " " + userCredit.getLastName())
                       .accountBalance(userCredit.getAccountBalance())
                       .accountNumber(request.getAccountNumber())

                       .build())


               .build();


    }

    @Override
    public BankResponse debitAccount(CreditDebitRequest request) {
        boolean isAccountExist = repo.existsByAccountNumber(request.getAccountNumber());
        if(!isAccountExist){
            return BankResponse.builder()
                    .responseCode(AccountUtils.ACCOUNT_NOT_EXIST_CODE)
                    .responseMessage(AccountUtils.ACCOUNT_NOT_EXIST_MESSAGE)
                    .accountInfo(null)
                    .build();
        }

        User userDebit = repo.findByAccountNumber(request.getAccountNumber());
        BigInteger availableBalance = userDebit.getAccountBalance().toBigInteger();
        BigInteger debitAmount = request.getAmount().toBigInteger();
        if(availableBalance.intValue()<debitAmount.intValue()){
            return BankResponse.builder()

                    .responseCode(AccountUtils.INSUFFICIENT_BALANCE_CODE)
                    .responseMessage(AccountUtils.INSUFFICIENT_BALANCE_MESSAGE)
                    .accountInfo(null)
                    .build();
        }
        else {
            userDebit.setAccountBalance(userDebit.getAccountBalance().subtract(request.getAmount()));
            repo.save(userDebit);
            return BankResponse.builder().
             responseCode(AccountUtils.ACCOUNT_DEBITED_SUCCESS)
                    .responseMessage(AccountUtils.ACCOUNT_DEBITED_MESSAGE)
                    .accountInfo(AccountInfo.builder()
                            .accountNumber(request.getAccountNumber())
                            .accountName(userDebit.getFirstName() + " " + userDebit.getLastName())
                            .accountBalance(userDebit.getAccountBalance())


                            .build())
            .build();
        }

    }
}