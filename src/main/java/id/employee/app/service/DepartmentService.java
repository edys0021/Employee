package id.employee.app.service;

import id.employee.app.entity.Department;
import id.employee.app.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    // Get all departments
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    // Get department by id
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
    }

    // Create department
    public Department createDepartment(Department department) {
        return departmentRepository.save(department);
    }

    // Update department
    public Department updateDepartment(Long id, Department department) {
        Department existing = getDepartmentById(id);
        existing.setName(department.getName());
        return departmentRepository.save(existing);
    }

    // Delete department
    public void deleteDepartment(Long id) {
        Department department = getDepartmentById(id);
        departmentRepository.delete(department);
    }
}