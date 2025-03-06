package com.sai.jwttoken;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSessionContext;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jwt")
public class Controller {

    @GetMapping("/get")
    public String getJwtTokeb() throws Exception{
    	try {
            // Paths to keystore and truststore
            String keystorePath = "/path/to/client-keystore.jks";
            String keystorePassword = "keystore-password";
            String truststorePath = "/path/to/client-truststore.jks";
            String truststorePassword = "truststore-password";

            // Create SSLContext
            SSLContext sslContext = SSLContextUtil.createSSLContext(keystorePath, keystorePassword, truststorePath, truststorePassword);

            // Configure Jersey client
            Client client = ClientBuilder.newBuilder()
                    .sslContext(sslContext) // Set the SSLContext
                    .build();

            // Make HTTPS request with form-based authentication
            Form form = new Form();
            form.param("username", "your-username");
            form.param("password", "your-password");

            WebTarget target = client.target("https://example.com/login");
            Response response = target.request()
                    .post(Entity.form(form)); // Send form data

            // Print response
            System.out.println("Response status: " + response.getStatus());
            System.out.println("Response body: " + response.readEntity(String.class));
        } catch (Exception e) {
            e.printStackTrace();
        }
    	return "ok";
    }
}
