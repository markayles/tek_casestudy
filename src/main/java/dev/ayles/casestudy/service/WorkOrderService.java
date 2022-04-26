package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.entity.WorkOrderNote;
import dev.ayles.casestudy.database.repository.WorkOrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;
    @Autowired
    private EmployeeService employeeService;

    public List<WorkOrder> getAllWorkOrders(){
        List<WorkOrder> workOrderList = new ArrayList<>();
        workOrderList = workOrderRepository.findAll();

        return workOrderList;
    }

    public WorkOrder getWorkOrderById(Integer id){
        WorkOrder workOrder = workOrderRepository.findById(id);
        
        return workOrder;
    }

    public void save(WorkOrder workOrder){
        workOrder.setUpdateTime(new Date());
        workOrderRepository.save(workOrder);
    }

    public void addNote(String note, Integer workOrderId){
        WorkOrder workOrder = this.getWorkOrderById(workOrderId);
        List<WorkOrderNote> notes = workOrder.getWorkOrderNotes();

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Employee e = employeeService.getEmployeeByUsername(user.getUsername());

        WorkOrderNote newNote = new WorkOrderNote();
        newNote.setNote(note);
        newNote.setCreateDate(new Date());
        newNote.setWorkOrder(workOrder);
        newNote.setEmployee(e);
        notes.add(newNote);

        this.save(workOrder);
        log.info("Note added to workorder #" + workOrderId + ": " + newNote);
    }
}
