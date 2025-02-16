package org.example.bazadostrony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping("/register")
    public @ResponseBody ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {

        if (userRepository.findByUsername(username) != null) {
            return new ResponseEntity<>("Taki użytkownik już istnieje. Spróbuj ponownie.", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User newUser = new User(username, encodedPassword);

        userRepository.save(newUser);
        return new ResponseEntity<>("Rejestracja przebiegła pomyślnie. Przekierowuję do logowania.", HttpStatus.CREATED);
    }

    // Usunięto mapping "/main" aby uniknąć konfliktu – strona główna będzie obsługiwana przez ViewController
}
