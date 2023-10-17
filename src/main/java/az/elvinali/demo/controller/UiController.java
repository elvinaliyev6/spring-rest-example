package az.elvinali.demo.controller;

import az.elvinali.demo.dto.request.EmployeeRequest;
import az.elvinali.demo.dto.response.BaseResponse;
import az.elvinali.demo.dto.response.EmployeeResponse;
import az.elvinali.demo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "index";

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

    @GetMapping("addEmployee")
    public String addEmployee(){
        return "addEmployee";
    }

    @PostMapping("addEmployeeSubmit")
    public String addEmployee(@ModelAttribute("employee") EmployeeRequest employeeRequest){
        employeeService.saveEmployee(employeeRequest);
        return "redirect:/ui/getAll";
    }

    @GetMapping("deleteEmployee")
    public String deleteEmployee(@RequestParam(value = "employeeId") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return "redirect:/ui/getAll";
    }


}
