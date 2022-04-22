package dev.ayles.casestudy.controller;

import com.fasterxml.jackson.annotation.JsonView;
import dev.ayles.casestudy.JsonViews;
import dev.ayles.casestudy.database.entity.Customer;
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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Date;
import java.util.List;

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
    public ModelAndView createWorkOrderSubmit(CreateWorkOrderForm form) throws Exception {
        ModelAndView response = new ModelAndView();

        WorkOrder workOrder = new WorkOrder();
        workOrder.setType(form.getType());
        workOrder.setStatus(form.getStatus());
        workOrder.setCustomer(customerService.getCustomerById(form.getCustomerId()));
        workOrder.setAddress(addressService.getAddressById(form.getCustomerAddressId()));

        workOrderService.save(workOrder);

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

        response.setViewName("redirect:/workorder/view/" + workOrderId);
        return response;
    }

}
