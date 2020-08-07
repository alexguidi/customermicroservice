package com.guidi.customermicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.guidi.customermicroservice.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
    
}
