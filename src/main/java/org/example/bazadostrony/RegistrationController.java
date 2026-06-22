package org.example.bazadostrony;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public RegistrationController(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<String> processRegistration(@RequestParam("username") String username,
                                                      @RequestParam("password") String password) {
        if (userRepository.findByUsername(username).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Użytkownik o takiej nazwie już istnieje!");
        }

        User user = new User(username, passwordEncoder.encode(password));
        userRepository.save(user);

        return ResponseEntity.ok("Rejestracja przebiegła pomyślnie. Możesz się zalogować.");
    }
}