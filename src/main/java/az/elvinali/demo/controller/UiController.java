package az.elvinali.demo.controller;

import az.elvinali.demo.dto.response.BaseResponse;
import az.elvinali.demo.dto.response.EmployeeResponse;
import az.elvinali.demo.model.Employee;
import az.elvinali.demo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("ui")
@Controller
public class UiController {

    private final EmployeeService employeeService;

    public UiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("main")
    public String home() {
        return "main";
    }

    @GetMapping("/getAll")
    public String getAllEmployees(Model model) {
        BaseResponse<List<EmployeeResponse>> empListBaseResponse = employeeService.getAllEmployees();

        List<EmployeeResponse> employees = empListBaseResponse.getData();
        model.addAttribute("employeeList", employees);
        return "employees";

    }

    @GetMapping("/getByIdResponse")
    public String getAllEmployees(
            @RequestParam(value = "employeeId") Long employeeId,Model model
    ) {
        EmployeeResponse employeeResponse=employeeService.getEmployeeById(employeeId);
        model.addAttribute("employee",employeeResponse);
        return "getEmployeeByIdResponse";

    }

    @GetMapping("/getById")
    public String getAllEmployees(){
        return "getEmployeeById";
    }


}
