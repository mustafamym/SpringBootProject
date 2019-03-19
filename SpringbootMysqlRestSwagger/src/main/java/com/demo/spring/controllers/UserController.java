package com.demo.spring.controllers;

import com.demo.spring.dto.RestResponseDto;
import com.demo.spring.entity.User;
import com.demo.spring.services.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@Api(tags = ("UserController"))
@RestController
@RequestMapping(path="/users" ,produces = { "application/json" })
public class UserController {

    @Autowired
    UserService userService;

    @ApiOperation(value = "List of users", notes = "List all user")
    @GetMapping("/list")
    ResponseEntity<?> getAllUsers(Pageable pageable) {
        List<User> users = (List<User>)  userService.findAll(pageable);
          if (users.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<Object>(users, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "View User", notes = "Get User details")
    @GetMapping("/{id}")
    ResponseEntity<?> getUserById(@PathVariable(value = "id") Long id) {
        User user = userService.findById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @ApiOperation(value = "Create user", notes = "Create user")
    @PostMapping("/create")
    ResponseEntity<?> createNote(@Valid @RequestBody User user) {
        User createUser = userService.save(user);
        return new ResponseEntity<Object>(createUser, new HttpHeaders(), HttpStatus.OK);

    }

    @ApiOperation(value = "Update user", notes = "Update an user")
    @PutMapping("/{id}")
    ResponseEntity<?> updateNote(@PathVariable(value = "id") Long userId,
            @Valid @RequestBody User updateUser) {
        User getUser = userService.findById(userId);
        if (getUser == null) {
            return ResponseEntity.notFound().build();
        }
        updateUser.setId(getUser.getId());
        User updatedNote = userService.save(updateUser);
        return ResponseEntity.ok(updatedNote);
    }

    @ApiOperation(value = "Delete User", notes = "Deletes an User")
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {

        return ResponseEntity.ok(new RestResponseDto(userService.delete(id)));
    }
}
