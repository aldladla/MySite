package org.example.bazadostrony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String processRegistration(@RequestParam("username") String username,
                                      @RequestParam("password") String password,
                                      RedirectAttributes redirectAttributes) {
        if (userRepository.findByUsername(username) != null) {
            // Przekierowanie do /login z parametrem register=true oraz komunikatem o błędzie
            redirectAttributes.addFlashAttribute("error", "Taki użytkownik już istnieje. Spróbuj ponownie.");
            return "redirect:/login?register=true";
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User(username, encodedPassword);
        userRepository.save(user);

        redirectAttributes.addFlashAttribute("success", "Rejestracja przebiegła pomyślnie. Możesz się zalogować.");
        return "redirect:/login";
    }
}
