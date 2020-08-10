package com.guidi.customermicroservice.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.guidi.customermicroservice.model.Customer;

public interface CustomerService {

	public List<Customer> findAll(Pageable pageable);

	public Customer save(Customer customer);

	public Optional<Customer> findByID(String idNumber);

	public void removeCustomer(String idNumber);
}
