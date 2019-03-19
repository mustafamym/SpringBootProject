package com.spring.demo.repository;

import com.spring.demo.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;



public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Customer findByLoginAndActiveTrueAndDeletedFalse(String login);
}
