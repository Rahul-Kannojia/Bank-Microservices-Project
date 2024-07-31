package com.app.accounts.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDetailsDto {
    private String customerName;
    private String email;
    private String mobileNumber;
    private AccountsDto accountDto;
    private CardsDto cardsDto;
    private LoansDto loansDto;
}
