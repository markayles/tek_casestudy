package dev.ayles.casestudy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import dev.ayles.casestudy.database.entity.Address;
import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.entity.EmployeeRole;
import dev.ayles.casestudy.form.CreateAddressForm;
import dev.ayles.casestudy.form.CreateCustomerForm;
import dev.ayles.casestudy.form.RegisterForm;
import dev.ayles.casestudy.service.AddressService;
import dev.ayles.casestudy.service.CustomerService;
import dev.ayles.casestudy.service.EmployeeService;
import dev.ayles.casestudy.service.WorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Controller
public class EmployeeController {

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/employee/all")
    public ModelAndView viewAllEmployees() throws Exception {
        ModelAndView response = new ModelAndView();

        List<Employee> employees = employeeService.getAllEmployees();
        response.addObject("employees", employees);

        response.setViewName("/employee/viewAll");
        return response;
    }

    @GetMapping("/employee/view/{employeeId}")
    public ModelAndView viewCustomer(@PathVariable("employeeId") Integer employeeId) throws Exception {
        ModelAndView response = new ModelAndView();

        Employee employee = employeeService.getEmployeeById(employeeId);
        response.addObject("employee", employee);

        response.setViewName("/employee/view");
        return response;
    }

    @GetMapping("/employee/edit/{employeeId}")
    public ModelAndView editEmployee(@PathVariable("employeeId") Integer employeeId) throws Exception {
        ModelAndView response = new ModelAndView();

        Employee employee = employeeService.getEmployeeById(employeeId);

        response.addObject("employee", employee);
        response.setViewName("/employee/edit");
        return response;
    }

    @PostMapping("/employee/editSubmit")
    public ModelAndView editEmployeeSubmit(RegisterForm form,
                                           @RequestParam("id") Integer employeeId) throws Exception {
        ModelAndView response = new ModelAndView();

        Employee employee = employeeService.getEmployeeById(employeeId);
        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setTitle(form.getTitle());
        employee.setUsername(form.getUsername());

        // This is an awful way of checking if the role exists, but for the sake of time and lack of research, it will do for now
        for (String s : form.getRoles()) {
            boolean exists = false;
            for (EmployeeRole role : employee.getRoles()) {
                if (role.getEmployeeRole().equals(s)) {
                    exists = true;
                    break;
                }
            }

            if (!exists) {
                EmployeeRole newRole = new EmployeeRole();
                newRole.setEmployeeRole(s);
                newRole.setEmployee(employee);
                employee.getRoles().add(newRole);
                log.info("Adding role " + s + " to user " + employee.getUsername());
            }
        }

        // Again, awful way of doing this. Also for the sake of using a lambda, it was done this way
        List<EmployeeRole> rolesToRemove = new ArrayList<>();
        employee.getRoles().forEach(employeeRole -> {
            boolean exists = false;
            for (String s : form.getRoles()) {
                if (employeeRole.getEmployeeRole().equals(s)) {
                    exists = true;
                }
            }

            if (!exists) {
                rolesToRemove.add(employeeRole);
            }
        });

        for (EmployeeRole role : rolesToRemove) {
            employee.getRoles().remove(employee.getRoles().indexOf(role));
            log.info("Removing role " + role.getEmployeeRole() + " from user " + employee.getUsername());
        }

        boolean passwordFail = false;
        if (!form.getPassword().isEmpty()) {
            if (form.getPassword().equals(form.getConfirmPassword())) {
                String password = passwordEncoder.encode(form.getPassword());
                employee.setPassword(password);
            } else {
                passwordFail = true;
                response.addObject("form", form);
                response.addObject("passwordError", "Passwords do not match. Any other changed data has been saved.");
            }
        }

        log.info("Employee #" + employeeId + " have been updated: " + employee);
        employeeService.save(employee);

        if (passwordFail) {
            response.addObject("employee", employee);
            response.setViewName("/employee/edit");
        } else {
            response.setViewName("redirect:/employee/view/" + employeeId);
        }
        return response;
    }
}
