package id.employee.app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class EmployeeListResponse {

    private Long id;
    private String name;
    private String email;
    private String photo;
    private String departmentName;
    private Integer assessmentCount;
}