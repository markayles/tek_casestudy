package dev.ayles.casestudy.form;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateWorkOrderForm {
    @Length(min = 4, message="Type must be at least 4 characters")
    @NotBlank(message = "A type is required")
    private String type;

    @NotBlank(message = "A status is required")
    private String status;

    @Min(value = 1, message = "A customer must be assigned")
    private Integer customerId = 0;

    @Min(value = 1, message = "An address for work is required")
    private Integer customerAddressId = 0;
}
