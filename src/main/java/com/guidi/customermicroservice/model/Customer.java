package com.guidi.customermicroservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Customer {

    @Id
	private String idNumber;
    private String name;
    private String surname;
    private String email;
    private String initials;
    private String mobile;
    private Integer lastUpdated;

    public Customer(){
    }

    public Customer(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getIdNumber(){
        return idNumber;
    }

    public void setIdNumber(String idNumber){
        this.idNumber = idNumber;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getInitials(){
        return initials;
    }

    public void setInitials(String initials){
        this.initials = initials;
    }

    public String getMobile(){
        return mobile;
    }

    public void setMobile(String mobile){
        this.mobile = mobile;
    }

    public Integer getLastUpdated(){
        return lastUpdated;
    }

    public void setLastUpdated(Integer lastUpdated){
        this.lastUpdated = lastUpdated;
    }
}