package com.github.quanticc.gsheets;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.SheetsScopes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

@Configuration
public class SheetsConfiguration {

    private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS);

    @Bean
    public JsonFactory jsonFactory() {
        return JacksonFactory.getDefaultInstance();
    }

    @Bean
    public HttpTransport netHttpTransport() throws GeneralSecurityException, IOException {
        return GoogleNetHttpTransport.newTrustedTransport();
    }

    @Bean
    public Credential credential(JsonFactory jsonFactory, HttpTransport httpTransport,
                                 ApplicationProperties properties) throws URISyntaxException, IOException, GeneralSecurityException {
        Resource key = new ClassPathResource(properties.getSheets().getKeyFile());
        return new GoogleCredential.Builder()
                .setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setServiceAccountId(properties.getSheets().getAccount())
                .setServiceAccountPrivateKeyFromP12File(new File(key.getURL().toURI()))
                .setServiceAccountScopes(SCOPES)
                .build();
    }


}
