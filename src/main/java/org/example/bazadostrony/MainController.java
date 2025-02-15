package org.example.bazadostrony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/demo")
public class MainController {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @PostMapping(path="/register")
    public @ResponseBody ResponseEntity<String> registerUser(@RequestParam String username, @RequestParam String password) {

        if(userRepository.findByUsername(username) != null) {
            return new ResponseEntity<>("Taki użytkownik już istnieje. Spróbuj ponownie.", HttpStatus.BAD_REQUEST);
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User newUser = new User(username, encodedPassword);

        userRepository.save(newUser);
        return new ResponseEntity<>("Rejestracja przebiegła pomyślnie. Przekierowuję do logowania.", HttpStatus.CREATED);
    }

    @PostMapping(path="/login")
    public @ResponseBody String loginUser(@RequestParam String username, @RequestParam String password) {
        System.out.println("Próba logowania: " + username);

        User user = userRepository.findByUsername(username);

        if (user == null) {
            System.out.println("Użytkownik nie znaleziony!");
            return "User not found";
        }

        boolean passwordMatches = bCryptPasswordEncoder.matches(password, user.getPassword());

        if (passwordMatches) {
            System.out.println("Zalogowano poprawnie!");
            return "Login successful!";
        } else {
            System.out.println("Błędne hasło!");
            return "Invalid password";
        }
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/main")
    public String home() {
        return "main";
    }
}
