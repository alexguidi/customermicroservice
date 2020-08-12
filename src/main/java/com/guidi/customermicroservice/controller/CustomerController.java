package com.guidi.customermicroservice.controller;

import java.sql.Timestamp;
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
    
    private GenericResponse<Customer> genericResponse;
    
    @Autowired
    @Qualifier("v1")
    CustomerService customerServiceV1;
    
    @GetMapping(value = "/v1/customer")
    public ResponseEntity<GenericResponse<Customer>> findAll(@RequestParam("page") int page, 
              @RequestParam("size") int size, @RequestParam("sort") String sort, Pageable pageable) {
        List<Customer> customerList= new ArrayList<>();
        
        if(!customerServiceV1.findAll(pageable).isEmpty()) {
            customerList.addAll(customerServiceV1.findAll(pageable));	
            genericResponse = new GenericResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), "Customer List Found", customerList);
        }else {
            genericResponse = new GenericResponse<>(HttpStatus.NO_CONTENT.value(), "KO", "Customer List Not Found", customerList);   		
        }   	
        
        return ResponseEntity.ok().body(genericResponse);
    }

    @GetMapping(value = "/v1/customer/{idNumber}")
    public ResponseEntity<GenericResponse<Customer>> findById(@PathVariable String idNumber) {
        List<Customer> customerList = new ArrayList<>();
        
        Optional<Customer> customer = customerServiceV1.findByID(idNumber);
        
        if(customer.isPresent()) {
            customerList.add(customer.get());		
            genericResponse = new GenericResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), "Customer Found", customerList);
            
        }else {
            genericResponse = new GenericResponse<>(HttpStatus.NO_CONTENT.value(), "KO", "Customer Not Found", customerList);
        }	
        
        return ResponseEntity.ok().body(genericResponse);
    }
    
    @PostMapping(value = "/v1/customer")
    public ResponseEntity<GenericResponse<Customer>> create(@RequestBody Customer customer) {    	
        List<Customer> customerList= new ArrayList<>();
        customer.setLastUpdated(new Timestamp(System.currentTimeMillis()).getTime());
        
        Customer customerReturned = customerServiceV1.save(customer);
        
        if(customerReturned != null) {
            customerList.add(customerServiceV1.save(customer));
            genericResponse = new GenericResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), "Customer Created Successfully", customerList);	
        }else {
            genericResponse = new GenericResponse<>(HttpStatus.NO_CONTENT.value(), "KO", "Customer was not created", customerList);
        }
        
        return ResponseEntity.ok().body(genericResponse);
    }
    
    @DeleteMapping(value = "/v1/customer/{idNumber}")
    public ResponseEntity<GenericResponse<Customer>> delete(@PathVariable String idNumber) {
        List<Customer> customerList = new ArrayList<>();
        
        Optional<Customer> optionalCustomer = customerServiceV1.findByID(idNumber);
        
        if(optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setLastUpdated(new Timestamp(System.currentTimeMillis()).getTime());  		
            customerList.add(customer);
            
            customerServiceV1.removeCustomer(idNumber);
            
            genericResponse = new GenericResponse<>(HttpStatus.OK.value(), HttpStatus.OK.name(), "Customer Deleted Successfully", customerList);
        }else {
            genericResponse = new GenericResponse<>(HttpStatus.NOT_FOUND.value(), "KO", "Customer does not exist", customerList);
        }
        
        return ResponseEntity.ok().body(genericResponse);
    }
}