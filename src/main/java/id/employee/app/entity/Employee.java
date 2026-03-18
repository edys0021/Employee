package id.employee.app.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String photo;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @JsonIgnoreProperties({"employees", "skills"})
    private Department department;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Assessment> assessments;
}