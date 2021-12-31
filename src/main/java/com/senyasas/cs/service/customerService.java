package com.senyasas.cs.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class customerService {

    //private final myRepository myRepository;
    //private final myMapper myMapper;

    /*@Autowired
    CustomerRepository customerRepository;

    public Customer createCustomer(final Customer customer) {

        CustomerModel customerModel = new CustomerModel();
        BeanUtils.copyProperties(customer, customerModel);
        customerModel = customerRepository.save(customerModel);
        Customer customerData = new Customer();
        BeanUtils.copyProperties(customerModel, customerData);

        return customerData;
    }*/
}
