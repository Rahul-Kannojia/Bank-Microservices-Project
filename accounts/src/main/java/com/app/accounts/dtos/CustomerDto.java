package com.app.accounts.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CustomerDto {

    private String customerName;
    private String email;
    private String mobileNumber;
}
