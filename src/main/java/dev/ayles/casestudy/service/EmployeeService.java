package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees(){
        List<Employee> employeeList = new ArrayList<>();
        employeeList = employeeRepository.findAll();

        return employeeList;
    }

    public Employee getEmployeeById(Integer id){
        Employee employee = employeeRepository.findById(id);

        return employee;
    }

    public void save(Employee employee){
        employeeRepository.save(employee);
    }

}
