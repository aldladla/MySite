package org.example.bazadostrony;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${nasa.api.key}")
    private String nasaApiKey;

    @GetMapping("/projekty")
    public String projectsPage(Model model) {
        String nasaUrl = "https://api.nasa.gov/planetary/apod?api_key=" + nasaApiKey + "&count=1";
        Apod[] apods = restTemplate.getForObject(nasaUrl, Apod[].class);
        Apod apod = (apods != null && apods.length > 0) ? apods[0] : null;
        model.addAttribute("apod", apod);
        return "projects";
    }

    @GetMapping("/crypto-json")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> getCryptoData() {
        String url = "https://api.coingecko.com/api/v3/simple/price?ids=bitcoin,ethereum&vs_currencies=usd";
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> prices = restTemplate.getForObject(url, Map.class);
            return ResponseEntity.ok(prices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}