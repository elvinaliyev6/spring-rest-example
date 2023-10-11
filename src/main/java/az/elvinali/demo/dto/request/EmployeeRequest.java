package az.elvinali.demo.dto.request;

import az.elvinali.demo.enums.EmployeeGender;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeRequest {

    @NotNull(message = "Employee name can not be null")
    @NotEmpty(message = "Employee name can not be empty")
    String name;

    @NotNull(message = "Employee surname can not be null")
    @NotEmpty(message = "Employee surname can not be empty")
    String surname;

    String fatherName;
    Integer age;
    LocalDate dob;
    EmployeeGender gender;
    String city;
//data transfer object
}
