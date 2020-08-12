package com.guidi.customermicroservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.guidi.customermicroservice.model.Customer;

/**This is the interface responsible to interact with Mongodb.
 * An implementation of this class will be created in runtime in 
 * Service to interact with mongodb.
 * 
 * @author Alex Guidi
 *
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
    
}
