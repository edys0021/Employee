package id.employee.app.service;

import id.employee.app.dto.CreateEmployeeRequest;
import id.employee.app.dto.EmployeeDetailResponse;
import id.employee.app.dto.EmployeeListResponse;
import id.employee.app.dto.SkillAssessmentResponse;
import id.employee.app.entity.Employee;
import id.employee.app.entity.Department;
import id.employee.app.repository.EmployeeRepository;
import id.employee.app.repository.DepartmentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    public EmployeeService(EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee createEmployee(CreateEmployeeRequest request) {

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Employee employee = new Employee();

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhoto(request.getPhoto());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public List<EmployeeListResponse> getAllEmployees() {

        return employeeRepository.findAll()
                .stream()
                .map(emp -> new EmployeeListResponse(
                        emp.getId(),
                        emp.getName(),
                        emp.getEmail(),
                        emp.getPhoto(),
                        emp.getDepartment().getName(),
                        emp.getAssessments() != null ? emp.getAssessments().size() : 0))
                .toList();
    }

    public EmployeeDetailResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        EmployeeDetailResponse response = new EmployeeDetailResponse();

        response.setId(employee.getId());
        response.setName(employee.getName());
        response.setEmail(employee.getEmail());
        response.setPhoto(employee.getPhoto());
        response.setDepartmentName(employee.getDepartment().getName());

        List<SkillAssessmentResponse> skills = employee.getAssessments()
                .stream()
                .map(a -> new SkillAssessmentResponse(
                        a.getSkill().getId(),
                        a.getSkill().getName(),
                        a.getSkill().getBaseScore(),
                        a.getScore()))
                .toList();

        response.setSkills(skills);

        return response;
    }
}