package com.banking.dto;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {
    private String firstName;
    private String lastName;
    private String phnNumber;
    private String address;
    private String gender;
    private String stateOfOrigin;
    private String email;


}
