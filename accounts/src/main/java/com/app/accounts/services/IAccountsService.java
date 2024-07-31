package com.app.accounts.services;

import com.app.accounts.dtos.CustomerDetailsDto;
import com.app.accounts.dtos.CustomerDto;

public interface IAccountsService {

    void createAccount(CustomerDto customerDto);

    CustomerDetailsDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDetailsDto customerDetailsDto);

    boolean deleteAccount(String mobileNumber);
}
