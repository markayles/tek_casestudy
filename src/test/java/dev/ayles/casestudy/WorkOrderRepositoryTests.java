package dev.ayles.casestudy;

import dev.ayles.casestudy.database.entity.Customer;
import dev.ayles.casestudy.database.entity.WorkOrder;
import dev.ayles.casestudy.database.repository.CustomerRepository;
import dev.ayles.casestudy.database.repository.WorkOrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class WorkOrderRepositoryTests {

    @Autowired
    private WorkOrderRepository workOrderRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void addWorkOrderTest(){

        Customer customer = customerRepository.findById(2);

        WorkOrder workOrder = new WorkOrder();
        workOrder.setType("example");
        workOrder.setStatus("new");
        workOrder.setCustomer(customer);
        workOrder.setAddress(customer.getAddresses().get(0));
        workOrderRepository.save(workOrder);

        Assertions.assertThat(workOrderRepository.findById(workOrder.getId()).equals(workOrder.getId()));
    }

}
