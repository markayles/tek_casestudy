package dev.ayles.casestudy.validation;

import dev.ayles.casestudy.database.entity.Employee;
import dev.ayles.casestudy.database.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailUniqueImpl implements ConstraintValidator<EmailUnique, String> {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public void initialize(EmailUnique constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if ( StringUtils.isEmpty(value) ) {
            return true;
        }

        Employee employee = employeeRepository.findByUsername(value);

        return ( employee == null );
    }

}
