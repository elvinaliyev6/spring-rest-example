package az.elvinali.demo.dto.response;

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
    String gender;
    String city;
}
