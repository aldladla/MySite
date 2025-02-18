package org.example.bazadostrony;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/main")
    public String mainPage() {
        return "main";
    }

    @GetMapping("/kontakt")
    public String contactPage() {
        return "kontakt";
    }

    /*@GetMapping("/projekty")
    public String projectsPage() {
        return "projects";
    }*/ /*znajduje sie w CryptoController */

    @GetMapping("/omnie")
    public String oMniePage() {
        return "omnie";
    }

}
