package dev.ayles.casestudy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import dev.ayles.casestudy.database.entity.Address;
import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.entity.WorkOrderNote;
import dev.ayles.casestudy.form.CreateAddressForm;
import dev.ayles.casestudy.form.CreateCustomerForm;
import dev.ayles.casestudy.form.CreateWorkOrderForm;
import dev.ayles.casestudy.service.AddressService;
import dev.ayles.casestudy.service.CustomerService;
import dev.ayles.casestudy.service.EmployeeService;
import dev.ayles.casestudy.service.WorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Autowired
    private AddressService addressService;

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
    public ModelAndView createCustomerSubmit(CreateCustomerForm form) throws Exception {
        ModelAndView response = new ModelAndView();

        Customer customer = new Customer();
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());

        customerService.save(customer);
        log.info("New customer created: " + customer);

        response.setViewName("redirect:/customer/createAddress/" + customer.getId());
        return response;
    }

    @GetMapping("/customer/edit/{customerId}")
    public ModelAndView editCustomer(@PathVariable("customerId") Integer customerId) throws Exception {
        ModelAndView response = new ModelAndView();

        Customer customer = customerService.getCustomerById(customerId);

        response.addObject("customer", customer);
        response.setViewName("/customer/edit");
        return response;
    }

    @PostMapping("/customer/editSubmit")
    public ModelAndView editCustomerSubmit(CreateCustomerForm form,
                                            @RequestParam("id") Integer customerId) throws Exception {
        ModelAndView response = new ModelAndView();

        Customer customer = customerService.getCustomerById(customerId);
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());

        customerService.save(customer);
        log.info("Customer modified: " + customer);

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

    @GetMapping("/customer/createAddress/{customerId}")
    public ModelAndView createAddressForCustomer(@PathVariable("customerId") Integer customerId) throws Exception {
        ModelAndView response = new ModelAndView();

        Customer customer = customerService.getCustomerById(customerId);
        response.addObject("customer", customer);

        response.setViewName("/customer/createAddress");
        return response;
    }

    @PostMapping("/customer/createAddressSubmit")
    public ModelAndView createAddressSubmit(CreateAddressForm form) throws Exception {
        ModelAndView response = new ModelAndView();

        Address address = new Address();
        address.setStreet(form.getStreet());
        address.setCity(form.getCity());
        address.setState(form.getState());
        address.setZip(form.getZip());
        address.setCustomer(customerService.getCustomerById(form.getCustomerId()));

        addressService.save(address);
        log.info("Address created for customer #"+ form.getCustomerId() +": " + address);

        response.setViewName("redirect:/customer/view/" + form.getCustomerId());
        return response;
    }

}
