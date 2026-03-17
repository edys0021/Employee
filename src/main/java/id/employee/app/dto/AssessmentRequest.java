package id.employee.app.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssessmentRequest {

    private Long employeeId;
    private Long skillId;
    private Integer score;

}