package com.MicroserviceBank.accounts.service.impl;


import com.MicroserviceBank.accounts.repository.AccountsRepository;
import com.MicroserviceBank.accounts.repository.CustomerRepository;
import com.MicroserviceBank.accounts.constants.AccountsConstants;
import com.MicroserviceBank.accounts.dto.AccountsDTO;
import com.MicroserviceBank.accounts.dto.CustomerDTO;
import com.MicroserviceBank.accounts.entity.Accounts;
import com.MicroserviceBank.accounts.entity.Customer;
import com.MicroserviceBank.accounts.exception.CustomerAlreadyExsitsException;
import com.MicroserviceBank.accounts.exception.ResourceNotFoundException;
import com.MicroserviceBank.accounts.mapper.AccountsMapper;
import com.MicroserviceBank.accounts.mapper.CustomerMapper;
import com.MicroserviceBank.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.Random;

import static com.MicroserviceBank.accounts.mapper.AccountsMapper.mapToAccounts;
import static com.MicroserviceBank.accounts.mapper.CustomerMapper.mapToCustomerDTO;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {
    private final AccountsRepository accountsRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerDTO customerDTO) {
        Customer customer = CustomerMapper.mapToCustomer(customerDTO, new Customer());
        Optional<Customer> optionalCustomer = customerRepository.findByMobileNumber(customerDTO.getMobileNumber());
        if (optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExsitsException("Customer already exists with this mobileNumber" + customerDTO.getMobileNumber());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    @Override
    public CustomerDTO fetchAccountDetail(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "mobileNumber" ,mobileNumber)
        );

        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Account", "customerID" ,customer.getCustomerId().toString())
        );

        CustomerDTO customerDTO = mapToCustomerDTO(customer, new CustomerDTO());
        customerDTO.setAccountsDTO(AccountsMapper.mapToAccountsDTO(accounts, new AccountsDTO()));

        return customerDTO;


    }

    @Override
    public boolean updateAccount(CustomerDTO customerDTO) {
        boolean result = false;
        AccountsDTO accountsDTO = customerDTO.getAccountsDTO();
        if (accountsDTO != null) {
            Accounts accounts = accountsRepository.findById(accountsDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "accountNumber" ,accountsDTO.getAccountNumber().toString())
            );
            mapToAccounts(accountsDTO, accounts);
            accounts = accountsRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "customerId" ,customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDTO, customer);
            customerRepository.save(customer);
            result = true;
        }

        return result;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber" ,mobileNumber)
        );
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.delete(customer);
        return true;
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts accounts = new Accounts();
        accounts.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 100_000_000_0L + new Random().nextInt(900000000);

        accounts.setAccountNumber(randomAccNumber);
        accounts.setAccountType(AccountsConstants.SAVGINS);
        accounts.setBranchAddress(AccountsConstants.ADDRESS);
        return accounts;
    }


}
