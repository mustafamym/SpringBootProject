package com.demo.spring.services;

import com.demo.spring.entity.User;
import org.springframework.data.domain.Pageable;



public interface UserService {

    Iterable<User> findAll(Pageable pageable);

    User save(User user);

    User update(User user);

    User findById(Long id);

    User findByEmail(String email);

    Boolean delete(Long id);
}
