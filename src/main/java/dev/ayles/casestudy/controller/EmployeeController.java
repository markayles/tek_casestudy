package dev.ayles.casestudy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import dev.ayles.casestudy.database.entity.Address;
import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.Employee;
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
