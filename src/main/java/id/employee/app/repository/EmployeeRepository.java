package id.employee.app.repository;


import id.employee.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> findByNameContainingIgnoreCase(String name);

    List<Employee> findByEmailContainingIgnoreCase(String email);

    List<Employee> findByDepartmentId(Long departmentId);

}