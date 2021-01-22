package com.github.quanticc.gsheets;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private final Sheets sheets = new Sheets();

    public Sheets getSheets() {
        return sheets;
    }

    public static class Sheets {

        private String account;
        private String keyFile;

        public String getAccount() {
            return account;
        }

        public void setAccount(String account) {
            this.account = account;
        }

        public String getKeyFile() {
            return keyFile;
        }

        public void setKeyFile(String keyFile) {
            this.keyFile = keyFile;
        }
    }
}
