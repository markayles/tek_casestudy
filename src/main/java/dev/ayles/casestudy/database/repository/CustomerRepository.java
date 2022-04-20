package dev.ayles.casestudy.database.repository;

import dev.ayles.casestudy.database.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {



}
