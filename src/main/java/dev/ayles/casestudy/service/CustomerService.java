package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer getCustomerById(Integer id){
        Customer customer = customerRepository.findById(id);
        return customer;
    }

}
