
package com.demo.postsql.springjpa.services;

import com.demo.postsql.springjpa.entity.Customer;
import com.demo.postsql.springjpa.repo.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author mustafa
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Iterable<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable).getContent();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Boolean delete(Long id) {
        customerRepository.delete(id);
        return !customerRepository.exists(id);
    }

}
