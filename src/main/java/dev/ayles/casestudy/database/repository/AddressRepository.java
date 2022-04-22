package dev.ayles.casestudy.database.repository;

import dev.ayles.casestudy.database.entity.Address;
import dev.ayles.casestudy.database.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    public Address findById(@Param("address_id") Integer id);

}
