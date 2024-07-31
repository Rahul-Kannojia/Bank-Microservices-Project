package com.app.loans.servicesimpl;

import com.app.loans.dtos.LoansDto;
import com.app.loans.entities.Loans;
import com.app.loans.exceptions.LoanAlreadyExistsException;
import com.app.loans.exceptions.ResourceNotFoundException;
import com.app.loans.repositories.LoansRepository;
import com.app.loans.services.ILoansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
public class LoansServiceImpl implements ILoansService {
    @Autowired
    private LoansRepository loansRepository;

    @Override
    public String createLoan(String mobileNumber) {
        log.info("LoansServiceImpl :: createLoan : {}",mobileNumber);
        var loan = loansRepository.findByMobileNumber(mobileNumber);
        if(loan.isPresent()){
            throw new LoanAlreadyExistsException("Loan already exists with mobile number : "+mobileNumber);
        }
        Loans newLoan = createNewLoan(mobileNumber);
        loansRepository.save(newLoan);
        log.info("LoansServiceImpl :: createLoan :: Loan created Successfully with given mobile number: {}",mobileNumber);
        return "Loans Created Successfully";
    }

    private Loans createNewLoan(String mobileNumber) {
        log.info("LoansServiceImpl :: createNewLoan");
        Loans loans = new Loans();
        loans.setLoanType("HOME_LOAN");
        loans.setMobileNumber(mobileNumber);
        loans.setTotalLoan(500000);
        loans.setAmountPaid(0);
        loans.setOutStandingAmount(loans.getTotalLoan()-loans.getAmountPaid());
        Integer newLoanNumber = 1000000000+ new Random().nextInt(999999999);
        loans.setLoanNumber(String.valueOf(newLoanNumber));
        return loans;
    }

    @Override
    public LoansDto fetchLoan(String mobileNumber) {
        log.info("LoansServiceImpl :: fetchLoan : {}",mobileNumber);
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Loan Not Found with given mobile number :"+ mobileNumber));
        log.info("LoansServiceImpl :: fetchLoan : Loan fecthed successfully : {}",mobileNumber);
        LoansDto loansDto= new LoansDto();
        BeanUtils.copyProperties(loans,loansDto);
        return loansDto;
    }

    @Override
    public Boolean updateLoan(LoansDto loansDto) {
        log.info("LoansServiceImpl :: updateLoan : {}",loansDto.getMobileNumber());
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber()).orElseThrow(()-> new ResourceNotFoundException("Loan is not exist with given Loan Number: "+loansDto.getMobileNumber()));
        BeanUtils.copyProperties(loansDto,loans);
        loansRepository.save(loans);
        log.info("LoansServiceImpl :: updateLoan :: Loan updated successfully: {}",loansDto.getMobileNumber());
        return true;
    }

    @Override
    public Boolean deleteLoan(String mobileNumber) {
        log.info("LoansServiceImpl :: deleteLoan : {}", mobileNumber);
        Loans loans = loansRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Loan Not Found with given mobile number :"+ mobileNumber));
        loansRepository.deleteById(loans.getLoanId());
        log.info("LoansServiceImpl :: deleteLoan :: Loan deleted successfully:  {}", mobileNumber);
        return true;
    }
}
