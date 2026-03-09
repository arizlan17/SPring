package org.example.springbootassignment.controller;

import jakarta.validation.Valid;
import org.example.springbootassignment.dto.CreateCustomerDto;
import org.example.springbootassignment.dto.CustomerDto;
import org.example.springbootassignment.dto.UpdateCustomerDto;
import org.example.springbootassignment.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping()
    public List<CustomerDto>getAllCustomers(){
        return customerService.findAllCustomer();
    }

    @GetMapping("/{nic}")
        public ResponseEntity<CustomerDto> getCustomer(@PathVariable @Valid String nic){
        CustomerDto filteredCustomer = customerService.findCustomerByNIC(nic);
        return ResponseEntity.ok(filteredCustomer);
    }
        
        

    @PostMapping()
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CreateCustomerDto createCustomerDto){
        CustomerDto savedCustomer = customerService.createCustomer(createCustomerDto);
        return ResponseEntity.ok(savedCustomer);
    }

    @PatchMapping()
    public ResponseEntity<CustomerDto>updateCustomer(@Valid @RequestBody UpdateCustomerDto updateCustomerDto){
        CustomerDto updatedCustomer = customerService.updateCustomer(updateCustomerDto);
        return ResponseEntity.ok(updatedCustomer);

    }

    @DeleteMapping("/{nic}")
    public ResponseEntity<String> deleteCustomer(@PathVariable @Valid String nic){
        customerService.deleteCustomer(nic);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
