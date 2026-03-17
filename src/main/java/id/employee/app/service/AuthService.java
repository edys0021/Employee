package id.employee.app.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import id.employee.app.dto.AuthResponse;
import id.employee.app.dto.LoginRequest;
import id.employee.app.dto.RegisterRequest;
import id.employee.app.entity.Person;
import id.employee.app.repository.PersonRepository;
import id.employee.app.security.JwtUtil;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public String register(RegisterRequest request) {

        if (personRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        Person person = Person.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .build();

        personRepository.save(person);

        return "Register Success";
    }

    public AuthResponse login(LoginRequest request) {
        Person person = personRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), person.getPassword())) {
            throw new RuntimeException("Invalid Email or Password");
        }

        String token = jwtUtil.generateToken(person.getEmail());
        return new AuthResponse(token, person.getEmail());
    }
}
