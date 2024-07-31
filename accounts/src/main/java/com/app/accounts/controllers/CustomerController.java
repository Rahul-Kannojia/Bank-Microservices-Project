package com.app.accounts.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.accounts.dtos.CustomerDetailsDto;
import com.app.accounts.services.ICustomerService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api")
@Slf4j
public class CustomerController {
	
	@Autowired
	private ICustomerService iCustomerService;
	
	@GetMapping("/fetchCustomerDetails")
	public CustomerDetailsDto fetchCutomerDetails(@RequestParam String mobileNumber) {
		log.info("CustomerController :: fetchCutomerDetails: {} ",mobileNumber);
		return  iCustomerService.fetchCustomerDetails(mobileNumber);
	}

}
