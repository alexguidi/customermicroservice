package com.guidi.customermicroservice.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.service.CustomerService;


@RestController
@RequestMapping(value = "/api/", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
public class CustomerController {

    
	@Autowired
	@Qualifier("v1")
    CustomerService customerServiceV1;
    
    @RequestMapping(value = "/v1/customer", method = RequestMethod.GET)
	public Iterable<Customer> customerV1() {
		return customerServiceV1.findAll();
    }

    @RequestMapping(value = "/v1/customer/{idNumber}", method=RequestMethod.GET)
	public Optional<Customer> read(@PathVariable String idNumber) {
		return customerServiceV1.findByID(idNumber);
	}
    
    @RequestMapping(value = "/v1/customer", method = RequestMethod.POST)
	public Customer create(@RequestBody Customer customer) {
		return customerServiceV1.save(customer);
    }
    
    @RequestMapping(value = "/v1/customer/{idNumber}", method=RequestMethod.DELETE)
	public void delete(@PathVariable String idNumber) {
		customerServiceV1.removeCustomer(idNumber);
    }
}