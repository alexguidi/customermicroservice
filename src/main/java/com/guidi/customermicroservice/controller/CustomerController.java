package com.guidi.customermicroservice.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.model.GenericResponse;
import com.guidi.customermicroservice.services.CustomerService;


@RestController
@RequestMapping(value = "/api/")
public class CustomerController {

    
	@Autowired
	@Qualifier("v1")
    CustomerService customerServiceV1;
    
    @GetMapping(value = "/v1/customer")
	public ResponseEntity<GenericResponse<Customer>> findAll(@RequestParam("page") int page, 
			  @RequestParam("size") int size, @RequestParam("sort") String sort, Pageable pageable) {
    	List<Customer> customerList= new ArrayList<>();
    	
    	customerList.addAll(customerServiceV1.findAll(pageable));
    	
    	GenericResponse<Customer> genericResponse = new GenericResponse<>(HttpStatus.OK.value(), 
				HttpStatus.OK.name(),
				"Customer List Found",
				customerList);
    	
    	return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(value = "/v1/customer/{idNumber}")
	public ResponseEntity<GenericResponse<Customer>> findById(@PathVariable String idNumber) {
    	List<Customer> customerList = new ArrayList<>();
    	
    	Optional<Customer> customer = customerServiceV1.findByID(idNumber);
    	
    	if(customer.isPresent()) {
    		customerList.add(customer.get());
    	}
    	
    	GenericResponse<Customer> genericResponse = new GenericResponse<>(HttpStatus.OK.value(), 
				HttpStatus.OK.name(),
				"Customer Found",
				customerList);
    	
    	return ResponseEntity.ok().body(genericResponse);
	}
    
    @PostMapping(value = "/v1/customer")
	public ResponseEntity<GenericResponse<Customer>> create(@RequestBody Customer customer) {    	
    	List<Customer> customerList= new ArrayList<>();
    	customerList.add(customerServiceV1.save(customer));
    	
    	GenericResponse<Customer> genericResponse = new GenericResponse<>(HttpStatus.OK.value(), 
    															HttpStatus.OK.name(),
    															"Customer Created Successfully",
    															customerList);
    	
    	
    	return ResponseEntity.ok().body(genericResponse);
    }
    
    @DeleteMapping(value = "/v1/customer/{idNumber}")
	public ResponseEntity<GenericResponse<Customer>> delete(@PathVariable String idNumber) {
    	List<Customer> customerList = new ArrayList<>();
    	
    	Optional<Customer> customer = customerServiceV1.findByID(idNumber);
    	
    	if(customer.isPresent()) {
    		customerList.add(customer.get());
    	}
    	
    	GenericResponse<Customer> genericResponse = new GenericResponse<>(HttpStatus.OK.value(), 
				HttpStatus.OK.name(),
				"Customer Deleted Successfully",
				customerList);
    	
		customerServiceV1.removeCustomer(idNumber);
		
		return ResponseEntity.ok().body(genericResponse);
    }
}