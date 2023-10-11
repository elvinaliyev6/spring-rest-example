package az.elvinali.demo.dto.response;

import az.elvinali.demo.enums.EmployeeGender;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class EmployeeResponse {
    Long id;
    String name;
    String surname;
    String fatherName;
    Integer age;
    String dob;
    EmployeeGender gender;
    String city;
    Date createDate;
    Date updateDate;
}
