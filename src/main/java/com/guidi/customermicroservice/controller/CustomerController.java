package com.guidi.customermicroservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.services.CustomerService;


@RestController
@RequestMapping(value = "/api/")
public class CustomerController {

    
	@Autowired
	@Qualifier("v1")
    CustomerService customerServiceV1;
    
    @GetMapping(value = "/v1/customer")
	public Iterable<Customer> customerV1() {
		return customerServiceV1.findAll();
    }

    @GetMapping(value = "/v1/customer/{idNumber}")
	public Optional<Customer> read(@PathVariable String idNumber) {
		return customerServiceV1.findByID(idNumber);
	}
    
    @PostMapping(value = "/v1/customer")
	public Customer create(@RequestBody Customer customer) {
		return customerServiceV1.save(customer);
    }
    
    @DeleteMapping(value = "/v1/customer/{idNumber}")
	public void delete(@PathVariable String idNumber) {
		customerServiceV1.removeCustomer(idNumber);
    }
}