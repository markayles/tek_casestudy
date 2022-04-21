package dev.ayles.casestudy.controller;

import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.entity.WorkOrderNote;
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
import java.util.Set;

@Slf4j
@Controller
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/workorder/all")
    public ModelAndView viewAllWorkOrders() throws Exception {
        ModelAndView response = new ModelAndView();

        List<WorkOrder> workOrders = workOrderService.getAllWorkOrders();
        response.addObject("workOrders", workOrders);

        response.setViewName("/workorder/viewAll");
        return response;
    }

    @GetMapping("/workorder/view/{workOrderId}/")
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

//    @PostMapping(value = "/note/addNote", produces = "application/json")
//    @ResponseBody
//    public List<WorkOrderNote> addNote(@RequestParam("workOrderId") Integer workOrderId,
//                                       @RequestParam("addNote") String note) throws Exception {
//        ModelAndView response = new ModelAndView();
//
//        WorkOrder workOrder = workOrderService.getWorkOrderById(workOrderId);
//        List<WorkOrderNote> notes = workOrder.getWorkOrderNotes();
//
//        WorkOrderNote newNote = new WorkOrderNote();
//        newNote.setNote(note);
//        newNote.setCreateDate(new Date());
//        newNote.setWorkOrder(workOrder);
//        newNote.setEmployee(employeeService.getEmployeeById(1));
//        notes.add(newNote);
//
//        workOrderService.save(workOrder);
//
//
//
//        response.addObject("workOrder", workOrder);
//
//        response.setViewName("/workorder/view");
//        return notes;
//    }

}
