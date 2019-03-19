package com.spring.demo.jwt.security;

import com.spring.demo.entity.Customer;
import com.spring.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByLoginAndActiveTrueAndDeletedFalse(username);
        if (customer == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AuthUserPrincipal(customer);
    }

}
