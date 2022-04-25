package dev.ayles.casestudy.database.repository;

import dev.ayles.casestudy.database.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    public Employee findByUsername(@Param("username") String username);

    @Query(value = "SELECT * FROM employees e WHERE e.status = ?1", nativeQuery = true)
    public Employee findById(@Param("id") Integer id);

}
