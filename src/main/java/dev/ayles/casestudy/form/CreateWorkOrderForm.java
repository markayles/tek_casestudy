package dev.ayles.casestudy.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateWorkOrderForm {
    private String type;
    private String status;
    private Integer customerId;
}
