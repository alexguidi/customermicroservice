package com.guidi.customermicroservice.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.services.CustomerServiceV1Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 * This class is responsible to test the service but without a FakeMongoDb,
 * the tests are based on Mock objects with the value expected.
 *  
 * @author Alex Guidi
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceV1ImplTest {
    /**
     * Responsible to mock CustomerRepository, this time without a MongoDb.
     */
    @Mock
    private CustomerRepository customerRepository;
    
    /**
     * Responsible to mock CustomerServiceV1Impl.
     */
    @InjectMocks
    CustomerServiceV1Impl customerService;

    /**
     * This method is responsible to test findAll method. The method
     * will check if after call findAll method in service the returned 
     * information is the expected.
     */
    @Test
    public void findAll() {
        Pageable pageable = PageRequest.of(0, 2);
        
        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer("Alex", "Guidi", "guidowww@gmail.com"));
        Page<Customer> pages = new PageImpl(customerList);
        
        doReturn(pages).when(customerRepository).findAll(pageable);
        
        List<Customer> customers = customerService.findAll(pageable);
        
        assertThat(customers).isEqualTo(pages.getContent());
    }
    
    /**
     * This method is responsible to test save method. The method
     * will check if after call save method in service the returned 
     * information is the expected.
     */
    @Test
    public void save() {
        Customer customer = new Customer("Alex", "Guidi", "guidowww@gmail.com");
        doReturn(customer).when(customerRepository).save(customer);
        
        Customer customerFromService = customerService.save(customer);
        
        assertThat(customer).isEqualTo(customerFromService);
    }
    
    /**
     * This method is responsible to test findById method. The method
     * will check if after call findById method in service the returned 
     * information is the expected.
     */
    @Test
    public void findById() {
        Optional<Customer> customer = Optional.of(new Customer("Alex", "Guidi", "guidowww@gmail.com"));
        customer.get().setIdNumber("123456");
        doReturn(customer).when(customerRepository).findById(customer.get().getIdNumber());
        
        Optional<Customer> customerFromService = customerService.findByID("123456");
        
        assertThat(customer).isEqualTo(customerFromService);    
    }
    
    /**
     * This method is responsible to test removeCustomer method. The method
     * will check if after call removeCustomer method no issue happened, if 
     * any exception happens the method will not reach assertTrue(true) method
     * failing the test.
     */
    @Test
    public void removeCustomer() {
        customerService.removeCustomer("123");
        
        assertTrue(true);
    }
}
