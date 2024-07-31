package com.app.accounts.feignclients;

import org.springframework.stereotype.Component;

import com.app.accounts.dtos.LoansDto;

@Component
public class LoansFallback implements LoansFeignClient {

	@Override
	public LoansDto fetchLoanDetails(String mobileNumber) {
		LoansDto loansDto = new LoansDto();
		loansDto.setStatus("Please try after sometime");
		return loansDto;
	}

}
