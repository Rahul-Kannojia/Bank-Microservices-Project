package com.app.loans.controllers;

import com.app.loans.dtos.LoansDto;
import com.app.loans.services.ILoansService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Slf4j
public class LoansContoller {
    @Autowired
    private ILoansService iLoansService;

    @PostMapping(value = "/create")
    public String createLoan(@RequestParam String mobileNumber){
        log.info("CardsController :: createCard : {}",mobileNumber);
        return iLoansService.createLoan(mobileNumber);
    }
    @GetMapping(value = "/fetch")
    public LoansDto fetchLoan(@RequestParam String mobileNumber){
        log.info("CardsController :: fetchCard : {}",mobileNumber);
        return iLoansService.fetchLoan(mobileNumber);
    }
    @PutMapping(value = "/update")
    public Boolean updateLoan(@RequestBody LoansDto loansDto){
        log.info("CardsController :: updateCard : {}",loansDto.getMobileNumber());
        return iLoansService.updateLoan(loansDto);
    }
    @DeleteMapping(value = "/delete")
    public Boolean deleteLoan(@RequestParam String mobileNumber){
        log.info("CardsController :: deleteCard : {}",mobileNumber);
        return iLoansService.deleteLoan(mobileNumber);
    }
}
