package com.guidi.customermicroservice.model;

import org.springframework.data.annotation.Id;

/**This is the entity class responsible to represent a Customer
 * 
 * @author Alex Guidi
 *
 */
public class Customer {

    @Id
    private String idNumber;
    private String name;
    private String surname;
    private String email;
    private String initials;
    private String mobile;
    private Long lastUpdated;

    public Customer(){
    }

    public Customer(final String name, final String surname, final String email) {
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(final String idNumber) {
        this.idNumber = idNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(final String initials) {
        this.initials = initials;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(final String mobile) {
        this.mobile = mobile;
    }

    public Long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final Long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}