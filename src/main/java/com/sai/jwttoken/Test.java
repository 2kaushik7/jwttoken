package com.sai.jwttoken;


import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Test {
//    public static void main(String[] args) {
//        try {
//            HttpClient client = createHttpClientWithNoSSLValidation();
//            Map<Object, Object> data = new HashMap<>();
//            data.put("username", "username");
//            data.put("password", "password");
//
//            HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create("https://stats-uat.servicenow.net/stats-api/api/authenticate"))
//                .header("accept", "application/json, text/plain, */*")
//                .header("content-type", "application/x-www-form-urlencoded")
//                .POST(BodyPublishers.ofString(buildFormDataFromMap(data)))
//                .build();
//
//            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
//            System.out.println("Response Code: " + response.statusCode());
//            System.out.println("Response Body: " + response.body());
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    private static String buildFormDataFromMap(Map<Object, Object> data) {
        return data.entrySet()
            .stream()
            .map(entry -> URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8) + "=" + URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8))
            .collect(Collectors.joining("&"));
    }

    private static HttpClient createHttpClientWithNoSSLValidation() throws NoSuchAlgorithmException, KeyManagementException {
        TrustManager[] trustAllCerts = new TrustManager[] {
            new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }
        };

        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

        CookieManager cookieManager = new CookieManager(null, CookiePolicy.ACCEPT_ALL);
        CookieHandler.setDefault(cookieManager);


        return HttpClient.newBuilder()
            .sslContext(sslContext)
            .followRedirects(Redirect.ALWAYS)
            .cookieHandler(cookieManager)
            .build();
    }
}

