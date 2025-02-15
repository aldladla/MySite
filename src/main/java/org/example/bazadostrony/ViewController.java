package org.example.bazadostrony;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {
    @GetMapping("/main")
    public String mainPage() {
        return "main"; // main.html w src/main/resources/templates/
    }

    @GetMapping("/kontakt")
    public String contactPage() {
        return "kontakt"; // src/main/resources/templates/kontakt.html
    }

    @GetMapping("/projekty")
    public String projectsPage() {
        return "projects"; // src/main/resources/templates/projects.html
    }

    @GetMapping("/omnie")
    public String oMniePage() {
        return "omnie";
    }

}
