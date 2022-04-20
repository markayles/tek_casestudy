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
import java.util.Date;

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

    @RequestMapping(value = "/login/registerSubmit", method = { RequestMethod.POST })
    public ModelAndView registerSubmit(@Valid RegisterForm form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

        if (bindingResult.hasErrors()) {

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info( ((FieldError)error).getField() + " " +  error.getDefaultMessage());
            }

            // add the form back to the model so we can fill up the input fields
            // so the employee can correct the input and does not have type it all again
            response.addObject("form", form);

            // add the error list to the model
            response.addObject("bindingResult", bindingResult);

            // because there is 1 or more error we do not want to process the logic below
            // that will create a new employee in the database.   We want to show the register.jsp
            response.setViewName("login/registerForm");
            return response;
        }

        // we first assume that we are going to try to load the employee from
        // the database using the incoming id on the form
        Employee employee = employeeRepository.findById(form.getId());

        // if the employee is not null the know it is an edit
        if (employee == null) {
            // now, if the employee from the database is null then it means we did not
            // find this employee.   Therefore, it is a create.
            employee = new Employee();
        }

        employee.setUsername(form.getEmail());
        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setCreateTime(new Date());

        String password = passwordEncoder.encode(form.getPassword());
        employee.setPassword(password);

        employeeRepository.save(employee);

        // create and save the employee role object
        EmployeeRole userRole = new EmployeeRole();
        userRole.setEmployee(employee);
        userRole.setEmployeeRole("USER");

        employeeRoleRepository.save(userRole);

        log.info(form.toString());

        // here instaed of showing a view, we want to redirect to the edit page
        // the edit page will then be responsible for loading the employee from the
        // database and dynamically creating the page
        // when you use redirect: as part of the view name it triggers spring to tell the
        // browser to do a redirect to the URL after the :    The big piece here to
        // recognize that redirect: uses an actual URL rather than a view name path.
        response.setViewName("redirect:/employee/edit/" + employee.getId());

        return response;
    }
}
