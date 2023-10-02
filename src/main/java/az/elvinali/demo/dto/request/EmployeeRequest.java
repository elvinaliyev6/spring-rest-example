package az.elvinali.demo.dto.request;

import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EmployeeRequest {
    String name;
    String surname;
    String fatherName;
    Integer age;
    LocalDate dob;
    String gender;
    String city;
//data transfer object
}
