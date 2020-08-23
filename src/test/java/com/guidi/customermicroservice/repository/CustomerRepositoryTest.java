package com.guidi.customermicroservice.repository;

import static com.lordofthejars.nosqlunit.mongodb.MongoDbRule.MongoDbRuleBuilder.newMongoDbRule;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Optional;

import com.guidi.customermicroservice.config.FakeMongo;
import com.guidi.customermicroservice.model.Customer;
import com.lordofthejars.nosqlunit.annotation.UsingDataSet;
import com.lordofthejars.nosqlunit.core.LoadStrategyEnum;
import com.lordofthejars.nosqlunit.mongodb.MongoDbRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * This is the class responsible to test the application, the class uses 
 * FakeMongodb to avoid include unnecessary data in real databases.
 * 
 * @author Alex Guidi
 *
 */
@RunWith(SpringRunner.class)
@Import(value = { FakeMongo.class })
@EnableMongoRepositories(basePackageClasses = { CustomerRepository.class })
public class CustomerRepositoryTest {
    
    @Autowired
    private ApplicationContext applicationContext;

    /**A concrete object will be created during run time provided by 
     * spring framework.
     */
    @Autowired
    private CustomerRepository customerRepository;

    /**
     * Create the FakeMongoDb to run in background.
     */
    @Rule
    public MongoDbRule embeddedMongoDbRule = newMongoDbRule().defaultSpringMongoDb("mockDB");

    /**
     * This method check the scenario where no customer should exist
     * in MongoDb.
     */
    @Test
    @UsingDataSet(loadStrategy = LoadStrategyEnum.DELETE_ALL)
    public void noCustomersTest() {
        List<Customer> customers = customerRepository.findAll();
        assertTrue("List should be empty", customers.isEmpty());
    }
    
    /**
     * This method checks if after save 5 customer in an empty database
     * they are present when a findAll method is called.
     */
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
    
    /**
     * This method check if a customer is returned based in idNumber
     * parameter passed.
     * 
     * @throws Exception
     */
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