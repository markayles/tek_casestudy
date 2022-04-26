package dev.ayles.casestudy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.entity.WorkOrderNote;
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

        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);
        List<WorkOrderNote> notes = workOrder.getWorkOrderNotes();

        WorkOrderNote newNote = new WorkOrderNote();
        newNote.setNote(note);
        newNote.setCreateDate(new Date());
        newNote.setWorkOrder(workOrder);
        newNote.setEmployee(employeeService.getEmployeeById(1));
        notes.add(newNote);

        workOrderService.save(workOrder);
        log.info("Note added to workorder #" + workOrderId + ": " + newNote);

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
        workOrder.setType(form.getType());
        workOrder.setStatus(form.getStatus());
        workOrder.setCustomer(customerService.getCustomerById(form.getCustomerId()));
        workOrder.setAddress(addressService.getAddressById(form.getCustomerAddressId()));

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

        return ResponseEntity.ok().build();
    }
}
