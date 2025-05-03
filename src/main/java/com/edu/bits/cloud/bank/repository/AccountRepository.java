package com.edu.bits.cloud.bank.repository;


import com.edu.bits.cloud.bank.entity.Account;
import com.edu.bits.cloud.bank.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    List<Account> findByCustomer(Customer customer);
}
