package com.app.accounts.controllers;

import com.app.accounts.dtos.CustomerDetailsDto;
import com.app.accounts.dtos.CustomerDto;
import com.app.accounts.services.IAccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api")
@Slf4j
public class AccountsController {

    @Autowired
    private IAccountsService iAccountService;

    @PostMapping(value="/create")
    public String createAccount(@RequestBody CustomerDto customerDto){
        log.info("AccountController :: createAccount : {}",customerDto.getMobileNumber());
        iAccountService.createAccount(customerDto);
        return "Account Created Successfully";
    }
    @GetMapping("/fetch")
    public CustomerDetailsDto fetchAccount(@RequestParam String mobileNumber){
        log.info("AccountController :: fetchAccount : {}",mobileNumber);
        return iAccountService.fetchAccount(mobileNumber);
    }
    @PutMapping("/update")
    public boolean updateAccount(@RequestBody CustomerDetailsDto customerDetailsDto){
        log.info("AccountController :: updateAccount : {}",customerDetailsDto.getCustomerName());
        return iAccountService.updateAccount(customerDetailsDto);
    }

    @DeleteMapping("/delete")
    public boolean deleteAccount(@RequestParam String mobileNumber){
        log.info("AccountController :: deleteAccount : {}",mobileNumber);
        iAccountService.deleteAccount(mobileNumber);
        return true;
    }
}
