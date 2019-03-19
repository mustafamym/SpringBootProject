/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spring.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;

/**
 *
 * @author mustafa
 */
@Entity
public class Customer extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String login = "";
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password = "";
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String mobile = "";
    @Column(name = "admin", columnDefinition = "boolean default false", nullable = false)
    private Boolean admin = false;
    @Column(name = "superAdmin", columnDefinition = "boolean default false", nullable = false)
    private Boolean superAdmin = false;
    @Column(name = "active", columnDefinition = "boolean default true", nullable = false)
    private Boolean active = true;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    
    
    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getSuperAdmin() {
        return superAdmin;
    }

    public void setSuperAdmin(Boolean superAdmin) {
        this.superAdmin = superAdmin;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }

}
