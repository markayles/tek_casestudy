package dev.ayles.casestudy.form;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateAddressForm {
    private String street;
    private String city;
    private String state;
    private String zip;
    private Integer customerId;
}
