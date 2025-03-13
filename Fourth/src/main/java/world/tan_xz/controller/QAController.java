package world.tan_xz.controller;

/**
 * @author 谭轩钊
 * version 1.0
 */
import org.springframework.web.client.RestTemplate;

import org.springframework.http.*;

import org.springframework.web.bind.annotation.*;



@RestController

@RequestMapping("/qa")

public class QAController {
    private final RestTemplate restTemplate = new RestTemplate();
    @PostMapping("/ask")
    public ResponseEntity<String> askQuestion(@RequestBody String question) {
        String url = "http://你的阿里云IP:8000/ask";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestJson = "{\"question\": \"" + question + "\"}";
        HttpEntity<String> requestEntity = new HttpEntity<>(requestJson, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return response;
    }
}