package dev.ayles.casestudy.controller;

import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.entity.EmployeeRole;
import dev.ayles.casestudy.database.repository.EmployeeRepository;
import dev.ayles.casestudy.database.repository.EmployeeRoleRepository;
import dev.ayles.casestudy.form.RegisterForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Random;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private EmployeeRoleRepository employeeRoleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/login/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("login/loginForm");
        return response;
    }

    @RequestMapping(value = "/login/register", method = RequestMethod.GET)
    public ModelAndView create() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("login/registerForm");

        // all these 2 lines of code are doing is seeding the model with an
        // empty form bean so that the JSP page substitutions will not error out
        // in this case spring is being nice enough not to throw errors but these
        // 2 lines are safety.
        RegisterForm form = new RegisterForm();
        response.addObject("form", form);

        return response;
    }

    @RequestMapping(value = "/login/registerSubmit", method = {RequestMethod.POST})
    public ModelAndView registerSubmit(@Valid RegisterForm form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        if(!form.getPassword().equals(form.getConfirmPassword())){
            bindingResult.rejectValue("password", null, "Passwords do not match.");
            bindingResult.rejectValue("confirmPassword", null, "Passwords do not match.");
        }

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("Employee registration error: " + ((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);
            response.setViewName("login/registerForm");
            return response;
        }

        String username = form.getFirstName().substring(0, 1);
        username += form.getLastName();

        Random random = new Random();
        Employee e;
        int randomInt;

        // check if username already exists.. add different random numbers until it doesnt exist.
        // In theory this could result in an infinite loop, but extremely unlikely
        do {
            randomInt = random.nextInt(99) + 10; // get a 2 digit number from 10-99
            e = employeeRepository.findByUsername(username + randomInt);
        } while (e != null);

        username += randomInt;

        Employee employee = new Employee();
        employee.setUsername(username);
        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setTitle("New Employee");

        String password = passwordEncoder.encode(form.getPassword());
        employee.setPassword(password);

        employeeRepository.save(employee);

        // create and save the employee role object
        EmployeeRole userRole = new EmployeeRole();
        userRole.setEmployee(employee);
        userRole.setEmployeeRole("NEW_EMPLOYEE");

        employeeRoleRepository.save(userRole);
        log.info("New employee user created with username " + username + " - " + form.toString());

        response.addObject("employeeName", employee.getFirstName() + " " + employee.getLastName());
        response.addObject("username", employee.getUsername());
        response.setViewName("/login/registerAfter");
        return response;
    }
}
