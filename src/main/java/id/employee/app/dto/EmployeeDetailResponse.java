package id.employee.app.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Getter
@Setter
@JsonPropertyOrder({
    "id",
    "name",
    "email",
    "photo",
    "departmentName",
    "skills"
})
public class EmployeeDetailResponse {
    private Long id;
    private String name;
    private String email;
    private String photo;
    private String departmentName;
    private List<SkillAssessmentResponse> skills;

}