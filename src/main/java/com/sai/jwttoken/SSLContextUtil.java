package com.sai.jwttoken;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.security.KeyStore;

public class SSLContextUtil {
     
    public static SSLContext createSSLContext(String keystorePath, String keystorePassword,
                                             String truststorePath, String truststorePassword) throws Exception {
        // Load keystore
        KeyStore keyStore = KeyStore.getInstance("JKS");
        try (FileInputStream keyStoreInputStream = new FileInputStream(keystorePath)) {
            keyStore.load(keyStoreInputStream, keystorePassword.toCharArray());
        }

        // Load truststore
        KeyStore trustStore = KeyStore.getInstance("JKS");
        try (FileInputStream trustStoreInputStream = new FileInputStream(truststorePath)) {
            trustStore.load(trustStoreInputStream, truststorePassword.toCharArray());
        }

        // Initialize KeyManagerFactory with the keystore
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, keystorePassword.toCharArray());

        // Initialize TrustManagerFactory with the truststore
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(trustStore);

        // Initialize SSLContext
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(keyManagerFactory.getKeyManagers(), trustManagerFactory.getTrustManagers(), null);

        return sslContext;
    }
}
