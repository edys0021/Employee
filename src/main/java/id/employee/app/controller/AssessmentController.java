package id.employee.app.controller;

import id.employee.app.entity.Assessment;
import id.employee.app.service.AssessmentService;
import id.employee.app.dto.AssessmentRequest;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @GetMapping
    public List<Assessment> getAllAssessments() {
        return assessmentService.getAllAssessments();
    }

    @GetMapping("/{id}")
    public Assessment getAssessmentById(@PathVariable Long id) {
        return assessmentService.getAssessmentById(id);
    }

    @PostMapping
    public Assessment createAssessment(@RequestBody AssessmentRequest request) {
        System.out.println("CONTROLLER HIT");
        return assessmentService.createAssessment(request);
    }

    @PutMapping("/{id}")
    public Assessment updateAssessment(
            @PathVariable Long id,
            @RequestBody AssessmentRequest request
    ) {
        return assessmentService.updateAssessment(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteAssessment(@PathVariable Long id) {
        assessmentService.deleteAssessment(id);
        return "Assessment deleted";
    }
}