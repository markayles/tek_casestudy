package dev.ayles.casestudy.service;

import dev.ayles.casestudy.database.repository.WorkOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkOrderService {

    @Autowired
    private WorkOrderRepository workOrderRepository;

}
