package com.guidi.customermicroservice.services;

import java.util.Optional;

import com.guidi.customermicroservice.model.Customer;

public interface CustomerService {

	public Iterable<Customer> findAll();

	public Customer save(Customer customer);

	public Optional<Customer> findByID(String idNumber);

	public void removeCustomer(String idNumber);
}
