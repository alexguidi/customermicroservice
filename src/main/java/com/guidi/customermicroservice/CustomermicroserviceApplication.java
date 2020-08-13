package com.guidi.customermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;



/** This is the main class, the one responsible to keep the application running. 
 *
 * @author Alex Guidi
 *
 */
@SpringBootApplication
@RestController
public class CustomermicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomermicroserviceApplication.class, args);
    }

}
