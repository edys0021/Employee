package id.employee.app.service;

import id.employee.app.entity.Skill;
import id.employee.app.entity.Department;
import id.employee.app.repository.SkillRepository;
import id.employee.app.repository.DepartmentRepository;
import id.employee.app.dto.SkillRequest;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillService {

    private final SkillRepository skillRepository;
    private final DepartmentRepository departmentRepository;

    public SkillService(SkillRepository skillRepository, DepartmentRepository departmentRepository) {
        this.skillRepository = skillRepository;
        this.departmentRepository = departmentRepository;
    }

    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    public Skill getSkillById(Long id) {
        return skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));
    }

    public Skill createSkill(SkillRequest request) {

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        Skill skill = new Skill();
        skill.setName(request.getName());
        skill.setBaseScore(request.getBaseScore());
        skill.setMinScore(request.getMinScore());
        skill.setMaxScore(request.getMaxScore());
        skill.setDepartment(department);

        return skillRepository.save(skill);
    }

    public Skill updateSkill(Long id, SkillRequest request) {

        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));

        skill.setName(request.getName());
        skill.setBaseScore(request.getBaseScore());
        skill.setMinScore(request.getMinScore());
        skill.setMaxScore(request.getMaxScore());
        skill.setDepartment(department);

        return skillRepository.save(skill);
    }

    public void deleteSkill(Long id) {
        Skill skill = skillRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Skill not found"));

        skillRepository.delete(skill);
    }
}