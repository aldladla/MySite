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

    // Endpoint zwracający widok "projects" – można zmapować go na /projekty, jeśli chcesz
    @GetMapping("/projekty")
    public String projectsPage(Model model) {
        // Możesz dodać inne atrybuty do modelu, jeśli potrzebujesz
        return "projects"; // Szablon projects.html w katalogu templates
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
