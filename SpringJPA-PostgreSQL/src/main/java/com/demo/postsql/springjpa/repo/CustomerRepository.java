package com.demo.postsql.springjpa.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.postsql.springjpa.entity.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>{
	List<Customer> findByLastName(String lastName);
}
