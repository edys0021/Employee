package id.employee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SkillAssessmentResponse {

    private Long skillId;
    private String skillName;
    private Integer baseScore;
    private Integer score;
}