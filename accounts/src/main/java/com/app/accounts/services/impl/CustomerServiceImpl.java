package com.app.accounts.services.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.app.accounts.dtos.AccountsDto;
import com.app.accounts.dtos.CardsDto;
import com.app.accounts.dtos.CustomerDetailsDto;
import com.app.accounts.dtos.LoansDto;
import com.app.accounts.entities.Accounts;
import com.app.accounts.entities.Customer;
import com.app.accounts.exceptions.ResourceNotFoundException;
import com.app.accounts.feignclients.CardsFeignClient;
import com.app.accounts.feignclients.LoansFeignClient;
import com.app.accounts.repositories.AccountsRepository;
import com.app.accounts.repositories.CustomersRepository;
import com.app.accounts.services.ICustomerService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

	private CustomersRepository customersRepository;
	private AccountsRepository accountsRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;

	/**
	 * We are fetching Customer Details new response
	 * AccountInformation,CustomerInformation, LoansInformation, CardsInformation
	 */
	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
		log.info("CustomerServiceImpl :: fetchCustomerDetails : {}", mobileNumber);
		Customer customer = customersRepository.findByMobileNumber(mobileNumber).orElseThrow(
				() -> new ResourceNotFoundException("Customer With this mobile number is not exists: " + mobileNumber));
		Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(() -> new ResourceNotFoundException(
						"Account With this CustomerId does not exists: " + customer.getCustomerId()));
		CardsDto cardDto = cardsFeignClient.fetchCardDetails(mobileNumber);
		LoansDto loansDto = loansFeignClient.fetchLoanDetails(mobileNumber);

		return getCustomerDetailsDto(customer, accounts, cardDto, loansDto);
	}

	private CustomerDetailsDto getCustomerDetailsDto(Customer customer, Accounts accounts, CardsDto cardDto,
			LoansDto loansDto) {
		log.info("CustomerServiceImpl :: getCustomerDetailsDto :: Fetching CustomerDetailsDto Data ");
		CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
		BeanUtils.copyProperties(customer, customerDetailsDto);

		AccountsDto accountsDto = new AccountsDto();
		BeanUtils.copyProperties(accounts, accountsDto);

		customerDetailsDto.setAccountDto(accountsDto);

		setCardsDetails(cardDto, customerDetailsDto);
		setLoansDetails(loansDto, customerDetailsDto);
		log.info("CustomerServiceImpl :: getCustomerDetailsDto ::CustomerDetailsDto Data fetched successfully ");
		return customerDetailsDto;
	}

	private void setLoansDetails(LoansDto loansDto, CustomerDetailsDto customerDetailsDto) {
		log.info("CustomerServiceImpl :: setLoansDetails:: Setting Loans Details");
		if (loansDto != null) {
			customerDetailsDto.setLoansDto(loansDto);
		}
//		else {
//			log.error("CustomerServiceImpl :: setLoansDetails:: Loans Details is not available");
//			throw new ResourceNotFoundException("Loans Details is not available");
//		}
	}

	private void setCardsDetails(CardsDto cardDto, CustomerDetailsDto customerDetailsDto) {
		log.info("CustomerServiceImpl :: setCardsDetails:: Setting Cards Details");
		if (cardDto != null) {
			customerDetailsDto.setCardsDto(cardDto);
		}
		
//		else {
//			log.error("CustomerServiceImpl :: setCardsDetails:: Card Details is not available");
//			throw new ResourceNotFoundException("Card Details is not available");
//		}

	}

}
