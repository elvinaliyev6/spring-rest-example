package az.elvinali.demo.service;

import az.elvinali.demo.dto.request.EmployeeRequest;
import az.elvinali.demo.dto.response.BaseResponse;
import az.elvinali.demo.dto.response.EmployeeResponse;
import az.elvinali.demo.dto.response.ResponseStatus;
import az.elvinali.demo.exception.BaseException;
import az.elvinali.demo.mapper.EmployeeMapper;
import az.elvinali.demo.model.Employee;
import az.elvinali.demo.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public BaseResponse<Void> saveEmployee(EmployeeRequest employee) {

        String name = employee.getName();
        String surname = employee.getSurname();

        if (name == null || surname == null) {
            throw new BaseException("employee name or surname can not be null");
        }

        Employee emp = employeeMapper.mapRequestToEntity(employee);
        employeeRepository.save(emp);

        return BaseResponse.<Void>builder()
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<List<EmployeeResponse>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeResponse employeeResponse =employeeMapper.mapEntityToResponse(employee);

            employeeResponseList.add(employeeResponse);
        }

        if (employeeResponseList.isEmpty() || employeeResponseList == null) {
            throw new BaseException("Employee not found!");
        }

        return BaseResponse.<List<EmployeeResponse>>builder()
                .data(employeeResponseList)
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<Void> updateEmployee(EmployeeRequest employeeRequest, Long id) {
        Employee employee = getEmployeeById(id);

       Employee empEntity= employeeMapper.mapRequestToEntity(employeeRequest);
       empEntity.setId(id);

        employeeRepository.save(empEntity);

        return BaseResponse.<Void>builder()
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<EmployeeResponse> deleteEmployee(Long id) {
        Employee employee = getEmployeeById(id);
        employeeRepository.delete(employee);

        EmployeeResponse employeeResponse = employeeMapper.mapEntityToResponse(employee);

        return BaseResponse.<EmployeeResponse>builder()
                .data(employeeResponse)
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<List<EmployeeResponse>> deleteSelectedEmployees(List<Long> ids) {
        List<EmployeeResponse> employeeList = new ArrayList<>();
        for (Long id : ids) {
            Employee employee = getEmployeeById(id);
            if (employee == null) {
                throw new BaseException("employee not found with this id: " + id);
            }
            EmployeeResponse employeeResponse = employeeMapper.mapEntityToResponse(employee);
            employeeList.add(employeeResponse);
            employeeRepository.delete(employee);
        }

        return BaseResponse.<List<EmployeeResponse>>
                        builder()
                .data(employeeList)
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    private Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = employeeRepository.findById(id);

        if (employeeOptional.isPresent()) {
            throw new BaseException("Employee not found with this id: " + id);
        }

        return employeeOptional.get();
    }


}
