package dev.ayles.casestudy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import dev.ayles.casestudy.database.entity.Address;
import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.entity.WorkOrderNote;
import dev.ayles.casestudy.form.CreateCustomerForm;
import dev.ayles.casestudy.form.CreateWorkOrderForm;
import dev.ayles.casestudy.service.CustomerService;
import dev.ayles.casestudy.service.EmployeeService;
import dev.ayles.casestudy.service.WorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class CustomerController {

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/all")
    public ModelAndView viewAllCustomers() throws Exception {
        ModelAndView response = new ModelAndView();

        List<Customer> customers = customerService.getAllCustomers();
        response.addObject("customers", customers);

        response.setViewName("/customer/viewAll");
        return response;
    }

    @GetMapping("/customer/view/{customerId}")
    public ModelAndView viewCustomer(@PathVariable("customerId") Integer customerId) throws Exception {
        ModelAndView response = new ModelAndView();

        Customer customer = customerService.getCustomerById(customerId);
        Collections.reverse(customer.getWorkOrders());

        response.addObject("customer", customer);

        response.setViewName("/customer/view");
        return response;
    }

    @GetMapping("/customer/create")
    public ModelAndView createCustomer() throws Exception {
        ModelAndView response = new ModelAndView();

        response.setViewName("/customer/create");
        return response;
    }

    @PostMapping("/customer/createSubmit")
    public ModelAndView createWorkOrderSubmit(CreateCustomerForm form) throws Exception {
        ModelAndView response = new ModelAndView();

        Customer customer = new Customer();
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());

        customerService.save(customer);

        response.setViewName("redirect:/customer/view/" + customer.getId());
        return response;
    }

    @GetMapping("/customer/edit/{customerId}")
    public ModelAndView editWorkOrder(@PathVariable("customerId") Integer customerId) throws Exception {
        ModelAndView response = new ModelAndView();

        Customer customer = customerService.getCustomerById(customerId);

        response.addObject("customer", customer);
        response.setViewName("/customer/edit");
        return response;
    }

    @PostMapping("/customer/editSubmit")
    public ModelAndView editWorkOrderSubmit(CreateCustomerForm form,
                                            @RequestParam("id") Integer customerId) throws Exception {
        ModelAndView response = new ModelAndView();

        Customer customer = customerService.getCustomerById(customerId);
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());

        customerService.save(customer);

        response.setViewName("redirect:/customer/view/" + customerId);
        return response;
    }

    @RequestMapping(value = "/customer/getAddressesForCustomer/{customerId}", produces = "application/json", method = RequestMethod.GET)
    @JsonView(JsonViews.CustomerAddressesAJAX.class)
    @ResponseBody
    public List<Address> getAddressesForCustomer(@PathVariable("customerId") Integer customerId) throws Exception {

        Customer customer = customerService.getCustomerById(customerId);
        List<Address> addresses = customer.getAddresses();

        //Collections.reverse(addresses);

        return addresses;
    }

}
