package com.sai.jwttoken;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class Controller {

    @GetMapping("/get")
    public String getJwtTokeb(){
        String username = "XXXXX";
        String password = "XXXXX";
        // Create a Jersey client
        ClientConfig clientConfig = new ClientConfig();
        HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(username, password);
        Client client = ClientBuilder.newClient(clientConfig);
        client.register(feature);

        try {
            // Make the HTTPS call
            Response response = client.target("https://stats-uat.xxxx.net:443/stats-api/api/authenticate")
                    .request(MediaType.APPLICATION_JSON)
                    .get();

            // Check the response status
            if (response.getStatus() == 200) {
                return response.readEntity(String.class); // Return the response body
            } else {
                throw new RuntimeException("Failed with HTTP error code: " + response.getStatus());
            }
        } finally {
            client.close();
        }
    }
}
