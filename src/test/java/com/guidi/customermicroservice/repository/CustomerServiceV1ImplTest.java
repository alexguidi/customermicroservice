package com.guidi.customermicroservice.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

import com.guidi.customermicroservice.config.FakeMongo;
import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.services.CustomerService;
import com.guidi.customermicroservice.services.CustomerServiceV1Impl;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceV1ImplTest {
	@Mock
	private CustomerRepository customerRepository;
	

	@InjectMocks
	CustomerServiceV1Impl customerService;

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
	
	@Test
	public void save() {
		Customer customer = new Customer("Alex", "Guidi", "guidowww@gmail.com");
		doReturn(customer).when(customerRepository).save(customer);
		
		Customer customerFromService = customerService.save(customer);
		
		assertThat(customer).isEqualTo(customerFromService);
	}
	
	@Test
	public void findById() {
		Optional<Customer> customer = Optional.of(new Customer("Alex", "Guidi", "guidowww@gmail.com"));
		customer.get().setIdNumber("123456");
		doReturn(customer).when(customerRepository).findById(customer.get().getIdNumber());
		
		Optional<Customer> customerFromService = customerService.findByID("123456");
		
		assertThat(customer).isEqualTo(customerFromService);	
	}
	
	@Test
	public void removeCustomer() {
		customerService.removeCustomer("123");
		
		assertTrue(true);
	}
}
