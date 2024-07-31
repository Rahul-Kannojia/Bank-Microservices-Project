package com.app.accounts.services;

import com.app.accounts.dtos.CustomerDetailsDto;

public interface ICustomerService {

	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber);
}
