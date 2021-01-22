package com.github.quanticc.gsheets;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.model.ValueRange;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SheetsController {

    private static final Logger log = LoggerFactory.getLogger(SheetsController.class);

    private final HttpTransport httpTransport;
    private final JsonFactory jsonFactory;
    private final Credential credential;
    private final String applicationName;

    public SheetsController(HttpTransport httpTransport, JsonFactory jsonFactory, Credential credential,
                            @Value("${spring.application.name}") String applicationName) {
        this.httpTransport = httpTransport;
        this.jsonFactory = jsonFactory;
        this.credential = credential;
        this.applicationName = applicationName;
    }

    @GetMapping("/sheets/{id}/{range}")
    public List<List<Object>> getSheets(@PathVariable String id, @PathVariable String range) throws IOException {
        List<List<Object>> rows = new ArrayList<>();
        Sheets service = new Sheets.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName(applicationName)
                .build();
        ValueRange response = service.spreadsheets().values()
                .get(id, range)
                .execute();
        List<List<Object>> values = response.getValues();
        try {
            for (int i = 1; i < values.size(); i++) {
                rows.add(values.get(i));
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return rows;
    }
}
