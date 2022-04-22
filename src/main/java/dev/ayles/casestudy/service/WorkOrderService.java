package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.repository.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

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
}
