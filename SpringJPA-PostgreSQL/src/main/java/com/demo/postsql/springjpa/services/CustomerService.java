/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.postsql.springjpa.services;

import com.demo.postsql.springjpa.entity.Customer;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author mustafa
 */
public interface CustomerService {
     Iterable<Customer> findAll(Pageable pageable);

    Customer findById(Long id);

    Customer save(Customer customer);

    Boolean delete(Long id);
}
