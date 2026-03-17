package id.employee.app.service;

import id.employee.app.entity.Assessment;
import id.employee.app.entity.Employee;
import id.employee.app.entity.Skill;
import id.employee.app.repository.AssessmentRepository;
import id.employee.app.repository.EmployeeRepository;
import id.employee.app.repository.SkillRepository;
import id.employee.app.dto.AssessmentRequest;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class AssessmentService {

        private final AssessmentRepository assessmentRepository;
        private final EmployeeRepository employeeRepository;
        private final SkillRepository skillRepository;

        public AssessmentService(
                        AssessmentRepository assessmentRepository,
                        EmployeeRepository employeeRepository,
                        SkillRepository skillRepository) {

                this.assessmentRepository = assessmentRepository;
                this.employeeRepository = employeeRepository;
                this.skillRepository = skillRepository;
        }

        public List<Assessment> getAllAssessments() {
                return assessmentRepository.findAll();
        }

        public Assessment getAssessmentById(Long id) {
                return assessmentRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Assessment not found"));
        }

        public Assessment createAssessment(AssessmentRequest request) {
                System.out.println("SERVICE HIT");

                Employee employee = employeeRepository.findById(request.getEmployeeId())
                                .orElseThrow(() -> {
                                        System.out.println("EMPLOYEE NOT FOUND");
                                        return new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
                                });

                Skill skill = skillRepository.findById(request.getSkillId())
                                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                                                "Skill not found"));

                // VALIDASI DEPARTMENT
                if (!employee.getDepartment().getId().equals(skill.getDepartment().getId())) {
                        throw new ResponseStatusException(
                                        HttpStatus.BAD_REQUEST,
                                        "Skill does not belong to employee department");
                }

                Assessment assessment = new Assessment();
                assessment.setEmployee(employee);
                assessment.setSkill(skill);
                assessment.setScore(request.getScore());

                return assessmentRepository.save(assessment);
        }

        public Assessment updateAssessment(Long id, AssessmentRequest request) {

                Assessment assessment = assessmentRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Assessment not found"));

                Employee employee = employeeRepository.findById(request.getEmployeeId())
                                .orElseThrow(() -> new RuntimeException("Employee not found"));

                Skill skill = skillRepository.findById(request.getSkillId())
                                .orElseThrow(() -> new RuntimeException("Skill not found"));

                assessment.setEmployee(employee);
                assessment.setSkill(skill);
                assessment.setScore(request.getScore());

                return assessmentRepository.save(assessment);
        }

        public void deleteAssessment(Long id) {
                Assessment assessment = assessmentRepository.findById(id)
                                .orElseThrow(() -> new RuntimeException("Assessment not found"));

                assessmentRepository.delete(assessment);
        }
}