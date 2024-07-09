package com.banking.utils;

import java.time.Year;

public class AccountUtils {

    /**
     * current year + any random six digits
     */



    public static final String ACCOUNT_EXISTS_CODE="001";
    public static final String ACCOUNT_EXISTS_MESSAGE="This User Already has an Account Created";
    public static final String ACCOUNT_CREATION_SUCCESS="002";
    public static final String ACCOUNT_CREATION_MESSAGE="Account Has Been Successfully Created";
    public static final String ACCOUNT_FOUND_CODE ="004";
    public static final String ACCOUNT_FOUND_MESSAGE="User Account Found";
    public static final String ACCOUNT_NOT_EXIST_CODE = "003";
    public static final String ACCOUNT_NOT_EXIST_MESSAGE="User with the provided information not exists";

   public static final String ACCOUNT_CREDITED_SUCCESS = "005";
    public static final String ACCOUNT_CREDITED_SUCCESS_MESSAGE="User Account was Credited Successfully";
    public static final String INSUFFICIENT_BALANCE_CODE="006";
    public static final String INSUFFICIENT_BALANCE_MESSAGE="Your Account Doesn't have sufficient balance to produce check balance enquiry";

    public static final String ACCOUNT_DEBITED_SUCCESS ="007";
    public static final String ACCOUNT_DEBITED_MESSAGE="User Account Debited Successfully";
  public static final String TRANSFER_SUCCESSFUL_CODE ="008";
  public static final String TRANSFER_SUCCESSFUL_MESSAGE="Transfer Successful";

    public static String generateAccountNumber(){
       Year currentYear = Year.now();
       int min = 100000;
       int max = 999999;

       int randomNumber = (int) Math.floor(Math.random() * (max-min +1)+ min);

       String year = String.valueOf(currentYear);
       String rnumber= String.valueOf(randomNumber);
       StringBuilder accountNumber = new StringBuilder();
       return accountNumber.append(year).append(randomNumber).toString();

   }


}
