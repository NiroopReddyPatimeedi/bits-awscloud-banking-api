package com.edu.bits.cloud.bank.repository;

import com.edu.bits.cloud.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    // Custom query methods (if needed) can be added here.
}
