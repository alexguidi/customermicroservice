package com.guidi.customermicroservice.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.repository.CustomerRepository;

@Service("v1")
public class CustomerServiceV1Impl implements CustomerService {
	
	@Autowired
	CustomerRepository CustomerRepository;

	@Override
	public Iterable<Customer> findAll() {
		return CustomerRepository.findAll();
	}

	@Override
	public Customer save(Customer Customer) {
		return CustomerRepository.save(Customer);
	}

	@Override
	public Optional<Customer> findByID(String idNumber) {
		return CustomerRepository.findById(idNumber);
	}

	@Override
	public void removeCustomer(String idNumber) {
    	Customer st = new Customer();
    	st.setIdNumber(idNumber);
    	CustomerRepository.delete(st);		
	}
}
