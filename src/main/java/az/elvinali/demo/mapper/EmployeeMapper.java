package az.elvinali.demo.mapper;

import az.elvinali.demo.dto.request.EmployeeRequest;
import az.elvinali.demo.dto.response.EmployeeResponse;
import az.elvinali.demo.enums.EmployeeGender;
import az.elvinali.demo.model.Employee;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class EmployeeMapper {

    public EmployeeResponse mapEntityToResponse(Employee employee){
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .surname(employee.getSurname())
                .age(employee.getAge())
                .dob(String.valueOf(employee.getDob()))
                .gender(employee.getGender())
                .fatherName(employee.getFatherName())
                .city(employee.getCity())
                .createDate(employee.getCreateDate())
                .updateDate(employee.getUpdateDate())
                .build();
    }

    public Employee mapRequestToEntity(EmployeeRequest employeeRequest){
        return Employee
                .builder()
                .name(employeeRequest.getName())
                .surname(employeeRequest.getSurname())
                .age(employeeRequest.getAge())
                .dob(employeeRequest.getDob())
                .gender(employeeRequest.getGender())
                .fatherName(employeeRequest.getFatherName())
                .city(employeeRequest.getCity())
                .build();
    }

}
