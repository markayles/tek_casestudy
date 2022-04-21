package dev.ayles.casestudy.controller;

import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.service.WorkOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class WorkOrderController {

    @Autowired
    private WorkOrderService workOrderService;

    @GetMapping("/workorder/all")
    public ModelAndView viewAll() throws Exception {
        ModelAndView response = new ModelAndView();

        List<WorkOrder> workOrders = workOrderService.getAllWorkOrders();
        response.addObject("workOrders", workOrders);

        response.setViewName("/workorder/viewAll");
        return response;
    }

}
