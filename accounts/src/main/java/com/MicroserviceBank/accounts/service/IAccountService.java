package com.MicroserviceBank.accounts.service;

import com.MicroserviceBank.accounts.dto.CustomerDTO;
import com.MicroserviceBank.accounts.entity.Customer;

public interface IAccountService {

    /**
    *
    * @param customerDTO - CustomerDto object
    */
    void createAccount(CustomerDTO customerDTO);
    /**
     *
     * @param mobileNumber - Input Mobile Number
     * @return Account Details
     */
    CustomerDTO fetchAccountDetail(String mobileNumber);
    /**
     *
     * @param customerDTO - CustomerDto object
     * @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDTO customerDTO);

    boolean deleteAccount (String mobileNumber);
}
