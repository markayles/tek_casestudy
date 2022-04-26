package dev.ayles.casestudy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import dev.ayles.casestudy.database.entity.*;
import dev.ayles.casestudy.form.CreateWorkOrderForm;
import dev.ayles.casestudy.service.AddressService;
import dev.ayles.casestudy.service.CustomerService;
import dev.ayles.casestudy.service.EmployeeService;
import dev.ayles.casestudy.service.WorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasAuthority('EMPLOYEE')")
@Slf4j
@Controller
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AddressService addressService;

    @GetMapping("/workorder/create")
    public ModelAndView createWorkOrder() throws Exception {
        ModelAndView response = new ModelAndView();

        List<Customer> customers = customerService.getAllCustomers();
        response.addObject("customers", customers);

        response.setViewName("/workorder/create");
        return response;
    }

    @PostMapping("/workorder/createSubmit")
    public ModelAndView createWorkOrderSubmit(@Valid CreateWorkOrderForm form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("Work order creation error: " + ((FieldError) error).getField() + " " + error.getDefaultMessage());
            }
            List<Customer> customers = customerService.getAllCustomers();
            response.addObject("customers", customers);

            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);
            response.setViewName("workorder/create");
            return response;
        }

        WorkOrder workOrder = new WorkOrder();
        workOrder.setType(form.getType());
        workOrder.setStatus(form.getStatus());
        workOrder.setCustomer(customerService.getCustomerById(form.getCustomerId()));
        workOrder.setAddress(addressService.getAddressById(form.getCustomerAddressId()));

        workOrderService.save(workOrder);
        log.info("Work order created: " + workOrder);

        //response.addObject("workOrder", workOrder);

        response.setViewName("redirect:/workorder/view/" + workOrder.getId());
        return response;
    }

    @GetMapping("/workorder/all")
    public ModelAndView viewAllWorkOrders() throws Exception {
        ModelAndView response = new ModelAndView();

        List<WorkOrder> workOrders = workOrderService.getAllWorkOrders();
        response.addObject("workOrders", workOrders);

        response.setViewName("/workorder/viewAll");
        return response;
    }

    @GetMapping("/workorder/view/{workOrderId}")
    public ModelAndView viewWorkOrder(@PathVariable("workOrderId") Integer workOrderId) throws Exception {
        ModelAndView response = new ModelAndView();

        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);
        Collections.reverse(workOrder.getWorkOrderNotes());
//        workOrder.getWorkOrderNotes().sort((o1, o2) -> {
//            return o1.getId().compareTo(o2.getId());
//        });

        response.addObject("workOrder", workOrder);

        response.setViewName("/workorder/view");
        return response;
    }

    @RequestMapping(value = "/note/addNote", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addNote(@RequestParam("workOrderId") Integer workOrderId,
                                  @RequestParam("addNote") String note) throws Exception {
        workOrderService.addNote(note, workOrderId, false);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/note/getNotes/{workOrderId}", produces = "application/json", method = RequestMethod.GET)
    @JsonView(JsonViews.WorkOrderNoteAJAX.class)
    @ResponseBody
    public List<WorkOrderNote> getNotesJSON(@PathVariable("workOrderId") Integer workOrderId) throws Exception {

        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);
        List<WorkOrderNote> notes = workOrder.getWorkOrderNotes();
        Collections.reverse(notes);

        return notes;
    }

    @GetMapping("/workorder/edit/{workOrderId}")
    public ModelAndView editWorkOrder(@PathVariable("workOrderId") Integer workOrderId) throws Exception {
        ModelAndView response = new ModelAndView();

        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);
        Collections.reverse(workOrder.getWorkOrderNotes());
        response.addObject("workOrder", workOrder);

        List<Customer> customers = customerService.getAllCustomers();
        response.addObject("customers", customers);

        List<Employee> employees = employeeService.getAllEmployees();
        List<Employee> nonAssigned = employees.stream().filter(e -> !e.getWorkOrders().contains(workOrder)).collect(Collectors.toList());
        response.addObject("employees", nonAssigned);

        response.setViewName("/workorder/edit");
        return response;
    }

    @PostMapping("/workorder/editSubmit")
    public ModelAndView editWorkOrderSubmit(CreateWorkOrderForm form,
                                            @RequestParam("id") Integer workOrderId) throws Exception {
        ModelAndView response = new ModelAndView();

        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);
        if(!workOrder.getType().equals(form.getType())){
            workOrderService.addNote("Type changed to \"" + form.getType() + "\" (was: \""+workOrder.getType()+"\")", workOrderId, true);
            workOrder.setType(form.getType());
        }

        if(!workOrder.getStatus().equals(form.getStatus())){
            workOrderService.addNote("Status changed to \"" + form.getStatus() + "\" (was: \""+workOrder.getStatus()+"\")", workOrderId, true);
            workOrder.setStatus(form.getStatus());
        }

        Customer newCustomer = customerService.getCustomerById(form.getCustomerId());
        if(!workOrder.getCustomer().equals(newCustomer)){
            workOrderService.addNote("Customer changed to (" + newCustomer.getId() + ") "+newCustomer.getFirstName()+" "+newCustomer.getLastName()+" (was: (" + workOrder.getCustomer().getId() + ") "+workOrder.getCustomer().getFirstName()+" "+workOrder.getCustomer().getLastName()+")", workOrderId, true);
            workOrder.setCustomer(newCustomer);
        }

        Address newAddress = addressService.getAddressById(form.getCustomerAddressId());
        if(!workOrder.getAddress().equals(newAddress)){
            workOrderService.addNote("Address changed to (" + newAddress.getId() + ") "+newAddress.getStreet()+" "+newAddress.getCity()+", "+newAddress.getState()+" (was: ("+workOrder.getAddress().getId()+") "+workOrder.getAddress().getStreet()+" "+workOrder.getAddress().getCity()+", "+workOrder.getAddress().getState()+")", workOrderId, true);
            workOrder.setAddress(newAddress);
        }

        workOrderService.save(workOrder);
        log.info("Workorder modified: " + workOrder);

        response.setViewName("redirect:/workorder/view/" + workOrderId);
        return response;
    }

    @RequestMapping(value = "/workorder/addEmployee", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity addEmployee(@RequestParam("workOrderId") Integer workOrderId,
                                      @RequestParam("employeeId") Integer employeeId) throws Exception {

        Employee employee = employeeService.getEmployeeById(employeeId);
        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);

        workOrder.getEmployees().add(employee);
        workOrderService.save(workOrder);

        log.info("Employee #" + employeeId + " assigned to workorder #" + workOrderId);
        workOrderService.addNote("Employee ("+employee.getFirstName() +" "+employee.getLastName()+") assigned to work order", workOrderId, true);

        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/workorder/removeEmployee", produces = "application/json", method = RequestMethod.POST)
    public ResponseEntity removeEmployee(@RequestParam("workOrderId") Integer workOrderId,
                                      @RequestParam("employeeId") Integer employeeId) throws Exception {

        Employee employee = employeeService.getEmployeeById(employeeId);
        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);

        workOrder.getEmployees().remove(employee);
        workOrderService.save(workOrder);

        log.info("Employee #" + employeeId + " removed from workorder #" + workOrderId);
        workOrderService.addNote("Employee ("+employee.getFirstName() +" "+employee.getLastName()+") removed from work order", workOrderId, true);

        return ResponseEntity.ok().build();
    }
}
