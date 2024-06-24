package fontys.ind.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/subscribe")
@CrossOrigin(origins={"http://localhost:5173", "https://kaloyankulov.netlify.app"})
public class MailchimpController {

//    @Value("${mailchimp.api.key}")
    private String mailchimpApiKey = "a8cf8f1b41b594cb57df916490095f87-us18";
//    @Value("${mailchimp.list.id}")
    private String mailchimpListId = "49565fc9c9";

    @PostMapping
    public ResponseEntity<String> subscribe(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");

        if (email == null || email.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email is required");
        }

        String url = "https://us18.api.mailchimp.com/3.0/lists/" + mailchimpListId + "/members";

        Map<String, Object> data = new HashMap<>();
        data.put("email_address", email);
        data.put("status", "subscribed");

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");
        headers.setBasicAuth("anystring", mailchimpApiKey);

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(data, headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.postForEntity(url, request, String.class);
            return ResponseEntity.ok("Subscription successful");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to subscribe");
        }
    }
}
