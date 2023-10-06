package az.elvinali.demo.controller;

import az.elvinali.demo.dto.request.EmployeeRequest;
import az.elvinali.demo.dto.response.BaseResponse;
import az.elvinali.demo.dto.response.EmployeeResponse;
import az.elvinali.demo.model.Employee;
import az.elvinali.demo.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
@RequiredArgsConstructor
public class EmployeeController {


    private final EmployeeService employeeService;

    @GetMapping
    public BaseResponse<List<EmployeeResponse>> getAllEmployees(){
        return employeeService.getAllEmployees();
    }

    //getEmployeeById

    @PostMapping
    public BaseResponse<Void> saveEmployee(@RequestBody @Valid EmployeeRequest employee){
        return employeeService.saveEmployee(employee);
    }

    @PutMapping("/{id}")
    public BaseResponse<Void> updateEmployee(@RequestBody @Valid EmployeeRequest employeeRequest,@PathVariable Long id){
       return employeeService.updateEmployee(employeeRequest,id);
    }

    @PatchMapping("/{id}")
    public BaseResponse<Void> updateEmployeePatch(@RequestBody EmployeeRequest employeeRequest,@PathVariable Long id){
//        return employeeService.updateEmployee(employeeRequest,id);
        return employeeService.updateEmployeePatch(employeeRequest,id);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<EmployeeResponse> deleteEmployee(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }

    @DeleteMapping("/deleteSelected")
    public BaseResponse<List<EmployeeResponse>> deleteSelectedEmployees(@RequestParam("ids") List<Long> ids) {
        return employeeService.deleteSelectedEmployees(ids);
    }

    @GetMapping("/test")
    public String test(){
        return "success";
    }

}
