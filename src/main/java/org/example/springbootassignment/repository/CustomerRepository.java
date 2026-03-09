package org.example.springbootassignment.repository;

import org.example.springbootassignment.dto.CustomerDto;
import org.example.springbootassignment.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer save(Customer customer);
    Optional<Customer> findByNicNumber(String nicNumber);

    Optional<Customer> findByIsActive(boolean isActive);
    List<Customer> findAllByIsActive(boolean isActive);

}

