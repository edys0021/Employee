package id.employee.app.repository;

import id.employee.app.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
public interface PersonRepository extends JpaRepository<Person, Long> {
    Optional<Person> findByEmail(String email);
}
