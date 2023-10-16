package az.elvinali.demo.service;

import az.elvinali.demo.dto.request.EmployeeRequest;
import az.elvinali.demo.dto.response.BaseResponse;
import az.elvinali.demo.dto.response.EmployeeResponse;
import az.elvinali.demo.dto.response.ResponseStatus;
import az.elvinali.demo.enums.EnumAvailableStatus;
import az.elvinali.demo.enums.ErrorCodeEnum;
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

        Employee emp = employeeMapper.mapRequestToEntity(employee);
        employeeRepository.save(emp);

        return BaseResponse.<Void>builder()
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<List<EmployeeResponse>> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue());
        List<EmployeeResponse> employeeResponseList = new ArrayList<>();

        for (Employee employee : employees) {
            EmployeeResponse employeeResponse = employeeMapper.mapEntityToResponse(employee);

            employeeResponseList.add(employeeResponse);
        }

        if (employeeResponseList.isEmpty() || employeeResponseList == null) {
            throw new BaseException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND);
        }

        return BaseResponse.<List<EmployeeResponse>>builder()
                .data(employeeResponseList)
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<Void> updateEmployee(EmployeeRequest employeeRequest, Long id) {
        getEmployeeByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());

        Employee empEntity = employeeMapper.mapRequestToEntity(employeeRequest);
        empEntity.setId(id);

        employeeRepository.save(empEntity);

        return BaseResponse.<Void>builder()
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<EmployeeResponse> deleteEmployee(Long id) {
        Employee employee = getEmployeeByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
        employee.setActive(EnumAvailableStatus.DEACTIVE.getValue());
        employeeRepository.save(employee);

        EmployeeResponse employeeResponse = employeeMapper.mapEntityToResponse(employee);

        return BaseResponse.<EmployeeResponse>builder()
                .data(employeeResponse)
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<List<EmployeeResponse>> deleteSelectedEmployees(List<Long> ids) {
        List<EmployeeResponse> employeeList = new ArrayList<>();
        for (Long id : ids) {
            Employee employee = getEmployeeByIdAndActive(id, EnumAvailableStatus.ACTIVE.getValue());
            if (employee == null) {
                throw new BaseException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND);
            }
            employee.setActive(EnumAvailableStatus.DEACTIVE.getValue());
            EmployeeResponse employeeResponse = employeeMapper.mapEntityToResponse(employee);
            employeeList.add(employeeResponse);
            employeeRepository.save(employee);
        }

        return BaseResponse.<List<EmployeeResponse>>
                        builder()
                .data(employeeList)
                .status(ResponseStatus.getSuccessMessage())
                .build();
    }

    public BaseResponse<Void> updateEmployeePatch(EmployeeRequest employeeRequest, Long id) {
        return null;
    }

    private Employee getEmployeeByIdAndActive(Long id, Integer active) {
        Optional<Employee> employeeOptional = employeeRepository.findByIdAndActive(id, active);

        if (!employeeOptional.isPresent()) {
            throw new BaseException(ErrorCodeEnum.EMPLOYEE_NOT_FOUND);
        }

        return employeeOptional.get();
    }

    public EmployeeResponse getEmployeeById(Long employeeId) {
        Employee employee = getEmployeeByIdAndActive(employeeId, EnumAvailableStatus.ACTIVE.getValue());
        return employeeMapper.mapEntityToResponse(employee);
    }
}
