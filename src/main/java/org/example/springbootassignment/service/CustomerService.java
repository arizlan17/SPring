package org.example.springbootassignment.service;

import org.example.springbootassignment.dto.CreateCustomerDto;
import org.example.springbootassignment.dto.CustomerDto;
import org.example.springbootassignment.dto.UpdateCustomerDto;
import org.example.springbootassignment.model.Customer;
import org.example.springbootassignment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public CustomerDto createCustomer(CreateCustomerDto createCustomerDto){
        Customer customer = Customer.builder().nicNumber(createCustomerDto.nic())
                .customerName(createCustomerDto.customerName())
                .emailAddress(createCustomerDto.emailAddress())
                .phoneNumber(createCustomerDto.phoneNumber())
                .build();
        return CustomerDto.from(customerRepository.save(customer));
    }


    public CustomerDto findCustomerByNIC(String NIC){
        Customer customer =  customerRepository.findByNicNumber(NIC)
                .orElseThrow(()-> new RuntimeException("Customer with NIC: " + NIC + " not found"));
        return CustomerDto.from(customer);
    }


    public List<CustomerDto> findAllCustomer(){
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(CustomerDto::from).toList();
    }


    public CustomerDto updateCustomer(UpdateCustomerDto updateCustomerDto){
        Customer customer = customerRepository.findByNicNumber(updateCustomerDto.nic())
                .orElseThrow(()-> new RuntimeException("Customer with ID: " + updateCustomerDto.nic() + " not found"));

        if (updateCustomerDto.customerName() != null)customer.setCustomerName(updateCustomerDto.customerName());
        if (updateCustomerDto.emailAddress()!= null)customer.setEmailAddress(updateCustomerDto.emailAddress());
        if (updateCustomerDto.phoneNumber()!= null)customer.setPhoneNumber(updateCustomerDto.phoneNumber());
        return CustomerDto.from(customerRepository.save(customer));
    }


    public void deleteCustomer( String Nic){
        Customer deletedCustomer = customerRepository.findByNicNumber(Nic)
                .orElseThrow(()-> new RuntimeException("Customer with NIC: " + Nic + " not found"));
        deletedCustomer.setActive(false);
        customerRepository.save(deletedCustomer);
    }

}
