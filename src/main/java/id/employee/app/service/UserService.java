package id.employee.app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import id.employee.app.dto.UserResponse;
import id.employee.app.entity.Person;
import id.employee.app.repository.PersonRepository;

@Service
public class UserService {
    private final PersonRepository personRepository;

    public UserService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<UserResponse> getAllUsers() {
        List<Person> persons = personRepository.findAll();

        return persons.stream()
                .map(person -> new UserResponse(
                        person.getName(),
                        person.getEmail()))
                .toList();
    }

}
