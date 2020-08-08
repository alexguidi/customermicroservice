package com.guidi.customermicroservice.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.repository.CustomerRepository;

@Service("v1")
public class CustomerServiceV1Impl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Iterable<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public Customer save(Customer Customer) {
		return customerRepository.save(Customer);
	}

	@Override
	public Optional<Customer> findByID(String idNumber) {
		return customerRepository.findById(idNumber);
	}

	@Override
	public void removeCustomer(String idNumber) {
    	Customer st = new Customer();
    	st.setIdNumber(idNumber);
    	customerRepository.delete(st);		
	}
}
