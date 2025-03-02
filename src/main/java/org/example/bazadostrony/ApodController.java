package org.example.bazadostrony;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Controller
public class ApodController {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String apiKey = "Sj35BMURZdbMbIJ8tbZdCinL2PCOQGnKqyeaDppk";

    @GetMapping("/apod")
    public String getApod(Model model) {
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey;
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
        System.out.println("Surowa odpowiedź: " + response.getBody());

        Apod apod = restTemplate.getForObject(url, Apod.class);
        model.addAttribute("apod", apod);
        return "projects";
    }

    @GetMapping("/apod-json")
    @ResponseBody
    public Apod getRandomApodJson() {
        String url = "https://api.nasa.gov/planetary/apod?api_key=" + apiKey + "&count=1";
        Apod[] apods = restTemplate.getForObject(url, Apod[].class);
        if (apods != null && apods.length > 0) {
            return apods[0];
        }
        return null;
    }
}
