package com.app.loans.services;

import com.app.loans.dtos.LoansDto;

public interface ILoansService {

    String createLoan(String mobileNumber);

    LoansDto fetchLoan(String mobileNumber);

    Boolean updateLoan(LoansDto loansDto);

    Boolean deleteLoan(String mobileNumber);
}
