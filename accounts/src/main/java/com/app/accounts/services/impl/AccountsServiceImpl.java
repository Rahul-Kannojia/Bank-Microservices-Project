package com.app.accounts.services.impl;

import com.app.accounts.dtos.AccountsDto;
import com.app.accounts.dtos.CustomerDetailsDto;
import com.app.accounts.dtos.CustomerDto;
import com.app.accounts.entities.Accounts;
import com.app.accounts.entities.Customer;
import com.app.accounts.enums.AccountsType;
import com.app.accounts.exceptions.CustomerAlreadyExistsException;
import com.app.accounts.exceptions.ResourceNotFoundException;
import com.app.accounts.repositories.AccountsRepository;
import com.app.accounts.repositories.CustomersRepository;
import com.app.accounts.services.IAccountsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
@Slf4j
public class AccountsServiceImpl implements IAccountsService {

    @Autowired
    private AccountsRepository accountsRepository;
    @Autowired
    private CustomersRepository customersRepository;
    
    @Override
    public void createAccount(CustomerDto customerDto) {
    	log.info("AccountServiceImpl :: createAccount: {}", customerDto.getMobileNumber());
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDto, customer);

        Optional<Customer> optionalCustomer = customersRepository.findByMobileNumber(customerDto.getMobileNumber());
        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer already registered with given mobile Number "+customerDto.getMobileNumber());
        }

        var dbCustomer = customersRepository.save(customer);
        var accounts = createNewAccount(dbCustomer);
        accountsRepository.save(accounts);
    }

    private Accounts createNewAccount(Customer dbCustomer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(dbCustomer.getCustomerId());
        accounts.setAccountType(AccountsType.SAVING);
        accounts.setBranchAddress("Hyderbad");
        long randonNumber = 1000000L + new Random().nextInt(999999);
        accounts.setAccountNumber(randonNumber);
        return accounts;
    }

    @Override
    public CustomerDetailsDto fetchAccount(String mobileNumber) {
        // customer inforamtoin & accoutns information
        Customer customer = customersRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Mobile Number not found"+mobileNumber));

        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Account not found for mobile number"+mobileNumber));

        CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();
        BeanUtils.copyProperties(customer, customerDetailsDto);

        AccountsDto accountsDto = new AccountsDto();
        BeanUtils.copyProperties(account, accountsDto);
        customerDetailsDto.setAccountDto(accountsDto);

        return customerDetailsDto;
    }

    @Override
    public boolean updateAccount(CustomerDetailsDto customerDetailsDto) {
        //boolean isUpdated;
        AccountsDto accountsDto = customerDetailsDto.getAccountDto();
        if(accountsDto != null) {
            Accounts accounts = accountsRepository.findById(accountsDto.getAccountNumber())
                    .orElseThrow(() -> new ResourceNotFoundException("Account not found "+accountsDto.getAccountNumber()));
            BeanUtils.copyProperties(accountsDto, accounts);
            accounts  = accountsRepository.save(accounts);
            Long customerId = accounts.getCustomerId();
            Customer customer = customersRepository.findById(customerId)
                    .orElseThrow(() -> new ResourceNotFoundException("Customer not found "+customerId));
            BeanUtils.copyProperties(customerDetailsDto,customer);
            customersRepository.save(customer);
            //isUpdated = true;
        } else {
            throw new RuntimeException("Account details are missing");
        }
        return true;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customersRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Mobile Number not found"+mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customersRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
