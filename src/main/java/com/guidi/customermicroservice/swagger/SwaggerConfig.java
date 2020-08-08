package com.guidi.customermicroservice.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Sets;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

  public static final Contact DEFAULT_CONTACT = new Contact("Alex Roberto Guidi", "https://github.com/alexguidi", "guidowww@gmail.com");
  
  public static final ApiInfo V1_API_INFO = new ApiInfo(
      "Customer Microservice 1.0", "Customer Microservice API", "1.0",
      "urn:tos", DEFAULT_CONTACT, 
      "Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0");
  
  @Bean
  public Docket swaggerCustomerApi() {
      return new Docket(DocumentationType.SWAGGER_2)
          .groupName("customermicroservice")
          .select()
              .apis(RequestHandlerSelectors.basePackage("com.guidi.customermicroservice.controller"))
              .paths(PathSelectors.ant("/api/v1/**"))
          .build()
          .produces(Sets.newHashSet(APPLICATION_JSON_VALUE))
          .consumes(Sets.newHashSet(APPLICATION_JSON_VALUE))
          .apiInfo(V1_API_INFO);
  }
}
