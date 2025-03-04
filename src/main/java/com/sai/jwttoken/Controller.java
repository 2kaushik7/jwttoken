package com.sai.jwttoken;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@RestController
@RequestMapping("/jwt")
public class Controller {

    @GetMapping("/get")
    public String getJwtTokeb(){
        // Create the Basic Authentication header
        String username = "XXXX";
        String password = "XXXX";
        String credentials = username + ":" + password;
        String base64Credentials = Base64.getEncoder().encodeToString(credentials.getBytes());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + base64Credentials);

        // Create the HTTP entity with headers
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Make the HTTPS call
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(
                "https://stats-uat.xxxx.net:443/stats-api/api/authenticate",
                HttpMethod.GET,
                entity,
                String.class
        );

        // Check the response status
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody(); // Return the response body
        } else {
            throw new RuntimeException("Failed with HTTP error code: " + response.getStatusCode());
        }
    }
}
