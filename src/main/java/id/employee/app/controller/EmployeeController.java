package id.employee.app.controller;

import id.employee.app.dto.CreateEmployeeRequest;
import id.employee.app.dto.EmployeeDetailResponse;
import id.employee.app.dto.EmployeeListResponse;
import id.employee.app.entity.Employee;
import id.employee.app.service.EmployeeService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody CreateEmployeeRequest request) {
        return employeeService.createEmployee(request);
    }

    @GetMapping
    public List<EmployeeListResponse> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeDetailResponse getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }
}