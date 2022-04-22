package dev.ayles.casestudy.controller;

import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.entity.WorkOrderNote;
import dev.ayles.casestudy.database.repository.CustomerRepository;
import dev.ayles.casestudy.database.repository.EmployeeRepository;
import dev.ayles.casestudy.database.repository.WorkOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private WorkOrderRepository workOrderRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping({"/", "/index"})
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUserName = authentication.getName();
            response.addObject("username", currentUserName);
            response.addObject("authorities", authentication.getAuthorities());
            response.addObject("principal", authentication.getPrincipal());
        }



        response.setViewName("index");
        return response;
    }

    @GetMapping("/addStuff")
    public ModelAndView addRandomData() throws Exception {
        ModelAndView response = new ModelAndView();

        log.info("====== ADDING RANDOM DATA ======");

        Customer customer = new Customer();
        customer.setFirstName("Mark");
        customer.setLastName("Ayles");
        customerRepository.save(customer);

        WorkOrder workOrder = new WorkOrder();
        workOrder.setType("drain unclog");
        workOrder.setStatus("pending");
        workOrder.setCreateTime(new Date());
        workOrder.setUpdateTime(new Date());
        workOrder.setCustomer(customer);
        workOrderRepository.save(workOrder);

        Employee employee = new Employee();
        employee.setFirstName("Mark");
        employee.setLastName("Ayles");
        employee.setTitle("President");
        employee.setCreateTime(new Date());
        employeeRepository.save(employee);

        for (int i = 0; i < 5; i++) {
            WorkOrderNote workOrderNote = new WorkOrderNote();
            workOrderNote.setNote("This is a random note (" + i + ")");
            List<WorkOrderNote> notes = workOrder.getWorkOrderNotes();
            if(notes == null){
                notes = new ArrayList<>();
            }
            notes.add(workOrderNote);
            workOrder.setWorkOrderNotes(notes);
            workOrderNote.setEmployee(employee);
            workOrderNote.setWorkOrder(workOrder);
        }

        workOrderRepository.save(workOrder);

        log.info("======= END RANDOM DATA ========");

        response.setViewName("index");
        return response;
    }

}
