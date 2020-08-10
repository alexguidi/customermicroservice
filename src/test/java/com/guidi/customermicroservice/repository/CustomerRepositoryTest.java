package com.guidi.customermicroservice.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.guidi.customermicroservice.config.FakeMongo;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

import com.guidi.customermicroservice.model.Customer;
import com.guidi.customermicroservice.repository.CustomerRepository;


@RunWith(SpringRunner.class)
@Import(value = { FakeMongo.class })
@EnableMongoRepositories(basePackageClasses = { CustomerRepository.class })
public class CustomerRepositoryTest {
	
	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private CustomerRepository customerRepository;

	@Rule
	public MongoDbRule embeddedMongoDbRule = newMongoDbRule().defaultSpringMongoDb("mockDB");

	@Test
	@UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
	public void noCustomersTest() {
		List<Customer> customers = customerRepository.findAll();
		assertTrue("List should be empty", customers.isEmpty());
	}
	
	@Test
	@UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
	public void saveCustomersTest() {
		int qt = 5;
		for (int i = 0; i < qt; i++) {
			customerRepository.save(new Customer());		
		}
		List<Customer> customers = customerRepository.findAll();
		assertEquals(qt, customers.size());
	}
	
	@Test
	@UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
	public void findById() throws Exception {		
		customerRepository.save(new Customer("Alex", "Guidi", "guidowww@gmail.com"));
		List<Customer> customers = customerRepository.findAll();
		Optional<Customer> st = customerRepository.findById(customers.get(0).getIdNumber());
		assertTrue(st.isPresent());
		assertEquals("Alex", st.get().getName());
	}

}