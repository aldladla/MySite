package org.example.bazadostrony;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Controller
public class CryptoController {

    private final RestTemplate restTemplate = new RestTemplate();
    // Klucz API NASA – zamień na swój, jeśli to konieczne
    private final String nasaApiKey = "Sj35BMURZdbMbIJ8tbZdCinL2PCOQGnKqyeaDppk";

    // Endpoint zwracający widok "projects" (pod /projekty) wraz z losowym zdjęciem NASA APOD
    @GetMapping("/projekty")
    public String projectsPage(Model model) {
        // Pobierz losowe dane NASA APOD, używając parametru count=1
        String nasaUrl = "https://api.nasa.gov/planetary/apod?api_key=" + nasaApiKey + "&count=1";
        Apod[] apods = restTemplate.getForObject(nasaUrl, Apod[].class);
        Apod apod = (apods != null && apods.length > 0) ? apods[0] : null;
        if (apod == null) {
            System.err.println("Otrzymano null z NASA APOD API!");
        } else {
            System.out.println("Otrzymano losowy tytuł APOD: " + apod.getTitle());
        }
        model.addAttribute("apod", apod);
        // Możesz dodać inne atrybuty do modelu, np. dane kryptowalut, jeśli chcesz
        return "projects"; // Widok projects.html w katalogu templates
    }

    // Endpoint zwracający dane kryptowalut jako JSON
    @GetMapping("/crypto-json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCryptoData() {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";
        try {
            Map<String, Object> prices = restTemplate.getForObject(url, Map.class);
            return ResponseEntity.ok(prices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
