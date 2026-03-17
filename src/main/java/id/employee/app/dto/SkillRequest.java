package id.employee.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillRequest {

    private String name;
    private Integer baseScore;
    private Integer minScore;
    private Integer maxScore;
    private Long departmentId;
}