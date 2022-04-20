package dev.ayles.casestudy.database.repository;

import dev.ayles.casestudy.database.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {



}
