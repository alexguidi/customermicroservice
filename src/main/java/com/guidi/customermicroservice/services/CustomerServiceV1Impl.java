package com.guidi.customermicroservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.repository.CustomerRepository;

@Service("v1")
public class CustomerServiceV1Impl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public List<Customer> findAll(Pageable pageable) {
		Page<Customer> pageCustomer = customerRepository.findAll(pageable);
		List<Customer> customerList = pageCustomer.getContent();
		
		return customerList;
	}

	@Override
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
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
