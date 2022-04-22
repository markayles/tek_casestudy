package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public List<Customer> getAllWorkOrders(){
        List<Customer> customerList = new ArrayList<>();
        customerList = customerRepository.findAll();

        return customerList;
    }

    public Customer getCustomerById(Integer id){
        Customer customer = customerRepository.findById(id);
        return customer;
    }

    public void save(Customer customer){
        customerRepository.save(customer);
    }

}
