package com.demo.postsql.springjpa.controllers;

import com.demo.postsql.springjpa.constants.ResponseTagName;
import com.demo.postsql.springjpa.dto.CustomerDto;
import com.demo.postsql.springjpa.entity.Customer;
import com.demo.postsql.springjpa.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;

@Api(tags = ("Activities"))
@RestController
@RequestMapping(path = "/customers", produces = {"application/json"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    private ModelMapper modelMapper;
    private LinkedHashMap<Object, Object> apiResponse;
    private LinkedHashMap<String, Object> serviceResponse;

    @ApiOperation(value = "Save Customer", notes = "Save Customer")
    @PostMapping
    ResponseEntity<?> post(@RequestBody CustomerDto customerDto) {
        serviceResponse = new LinkedHashMap<String, Object>();
        apiResponse = new LinkedHashMap<Object, Object>();
        modelMapper = new ModelMapper();

        Customer o = modelMapper.map(customerDto, Customer.class);
        Customer createCustomer = customerService.save(o);
        serviceResponse.put(ResponseTagName.CREATED, createCustomer);
        apiResponse.put(ResponseTagName.API_RESPONSE, serviceResponse);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
    }

    @ApiOperation(value = "View Customer", notes = "Get Customer details")
    @GetMapping("/{id}")
    ResponseEntity<?> get(@PathVariable("id") Long id) {
        serviceResponse = new LinkedHashMap<String, Object>();
        apiResponse = new LinkedHashMap<Object, Object>();

        Customer o = customerService.findById(id);

        if (o != null) {
            serviceResponse.put(ResponseTagName.RETRIEVED, 0);
            apiResponse.put(ResponseTagName.API_RESPONSE, serviceResponse);
            return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);

        }
        return ResponseEntity.notFound().build();
    }

    @ApiOperation(value = "List Customers", notes = "List all Customers")
    @GetMapping("/list")
    ResponseEntity<?> list(Pageable pageable) {

        serviceResponse = new LinkedHashMap<String, Object>();
        apiResponse = new LinkedHashMap<Object, Object>();

        List<Customer> users = (List<Customer>) customerService.findAll(pageable);

        serviceResponse.put(ResponseTagName.LIST, users);
        apiResponse.put(ResponseTagName.API_RESPONSE, serviceResponse);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);

    }

    @ApiOperation(value = "Update Customer", notes = "Update an Customer")
    @PutMapping("/{id}")
    ResponseEntity<?> put(@PathVariable("id") Long id, @RequestBody CustomerDto customerDto) {
        serviceResponse = new LinkedHashMap<String, Object>();
        apiResponse = new LinkedHashMap<Object, Object>();

        Customer o = modelMapper.map(customerDto, Customer.class);
        Customer updateCustomer = customerService.save(o);

        serviceResponse.put(ResponseTagName.RETRIEVED, updateCustomer);
        apiResponse.put(ResponseTagName.API_RESPONSE, serviceResponse);
        return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
    }
    
    @ApiOperation(value = "Delete Customer", notes = "Deletes an Customer")
    @DeleteMapping("/{id}")
    ResponseEntity<?> delete(@PathVariable("id") Long id) {
        serviceResponse = new LinkedHashMap<String, Object>();
        apiResponse = new LinkedHashMap<Object, Object>();

        boolean isDeleted = customerService.delete(id);
        if (isDeleted) {

            apiResponse.put(ResponseTagName.STATUS, Boolean.TRUE);
            apiResponse.put(ResponseTagName.MESSAGE, "Your statistics has been deleted successfully.");
            return new ResponseEntity<Object>(apiResponse, new HttpHeaders(), HttpStatus.OK);
        } else {

            return new ResponseEntity<Object>("error occured while delete", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

}
