package id.employee.app.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateEmployeeRequest {

    private String name;
    private String email;
    private String photo;
    private Long departmentId;

}