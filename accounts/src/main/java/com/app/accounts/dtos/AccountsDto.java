package com.app.accounts.dtos;

import com.app.accounts.enums.AccountsType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccountsDto {

    private Long accountNumber;
    private AccountsType accountType;
    private String branchAddress;
}
