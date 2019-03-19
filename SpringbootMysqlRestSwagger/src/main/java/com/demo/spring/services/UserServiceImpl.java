package com.demo.spring.services;

import com.demo.spring.entity.User;
import com.demo.spring.repository.UserRepository;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author Mustafa
 */
@Service
public class UserServiceImpl implements UserService {

    static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).getContent();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Boolean delete(Long id) {
        userRepository.delete(id);
        return !userRepository.exists(id);
    }

}
