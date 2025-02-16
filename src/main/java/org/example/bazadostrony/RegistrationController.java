package org.example.bazadostrony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String processRegistration(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      Model model) {
        if (userRepository.findByUsername(username) != null) {
            // Ustaw atrybuty modelu, aby wyświetlić komunikat oraz formularz rejestracji
            model.addAttribute("regError", "Użytkownik o takiej nazwie już istnieje!");
            model.addAttribute("showRegister", true);
            return "login"; // Zwracamy widok login (bez redirectu)
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword);
        userRepository.save(user);

        // Jeśli rejestracja się powiedzie, możesz przekierować do strony logowania
        return "redirect:/login?regSuccess=true";
    }
}
