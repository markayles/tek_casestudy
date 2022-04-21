package dev.ayles.casestudy.database.repository;

import dev.ayles.casestudy.database.entity.EmployeeRole;
import dev.ayles.casestudy.database.entity.WorkOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    public WorkOrder findById(@Param("employee_id") Integer id);

}
