package dev.ayles.casestudy.database.repository;

import dev.ayles.casestudy.database.entity.Address;
import dev.ayles.casestudy.database.entity.EmployeeRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRoleRepository extends JpaRepository<EmployeeRole, Long> {

    public List<EmployeeRole> findByEmployeeId(@Param("employee_id") Integer employeeId);

}
