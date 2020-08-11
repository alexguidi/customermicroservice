package com.guidi.customermicroservice.config;

import com.github.fakemongo.Fongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;

/**This class is responsible to create a fakeMongodb for tests purposes,
 * this database is created and deleted automatically only during the test 
 * phase, avoiding to include unnecessary data in real databases.
 * 
 * @author Alex Guidi
 *
 */
@Configuration
public class FakeMongo extends AbstractMongoConfiguration{

    @Override
    protected String getDatabaseName() {
        return "mockDB";
    }

    @Bean
    public MongoClient mongoClient() {
        Fongo fongo = new Fongo("mockDB");
        return fongo.getMongo();
    }

}