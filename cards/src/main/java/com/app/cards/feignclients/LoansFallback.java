package com.app.cards.feignclients;

import org.springframework.stereotype.Component;

import com.app.cards.dtos.LoansDto;

@Component
public class LoansFallback implements LoansFeignClient {

	@Override
	public LoansDto fetchLoan(String mobileNumber) {
		LoansDto loansDto = new LoansDto();
		loansDto.setStatus("Please try after sometimes");
		return null;
	}

}
