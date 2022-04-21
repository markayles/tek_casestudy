package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Integer id){
        Employee employee = employeeRepository.findById(id);

        return employee;
    }

}
