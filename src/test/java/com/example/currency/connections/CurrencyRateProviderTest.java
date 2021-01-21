package com.example.currency.connections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateProviderTest {

    private String predefinedJsonString = "{\n" +
            "    \"Realtime Currency Exchange Rate\": {\n" +
            "        \"1. From_Currency Code\": \"USD\",\n" +
            "        \"2. From_Currency Name\": \"United States Dollar\",\n" +
            "        \"3. To_Currency Code\": \"RUB\",\n" +
            "        \"4. To_Currency Name\": \"Russian Ruble\",\n" +
            "        \"5. Exchange Rate\": \"73.98580000\",\n" +
            "        \"6. Last Refreshed\": \"2021-01-21 17:46:38\",\n" +
            "        \"7. Time Zone\": \"UTC\",\n" +
            "        \"8. Bid Price\": \"73.98580000\",\n" +
            "        \"9. Ask Price\": \"73.98580000\"\n" +
            "    }\n" +
            "}";

    @Test
    void getsConnectionString() {
        String connectionString = new CurrencyRateProvider().getConnectionString("usd", "rub");
        System.out.println(connectionString);
    }
    @Test
    void downloadsData() {
        var provider = new CurrencyRateProvider();
        String connectionString = provider.getConnectionString("usd", "rub");
        String data = provider.downloadCurrencyData(connectionString);
        System.out.println(data);
    }

    @Test
    void parsesJson() {
        var provider = new CurrencyRateProvider();
        String connectionString = provider.getConnectionString("usd", "rub");
        String data = provider.downloadCurrencyData(connectionString);
        double rate = provider.parseData(data);
        System.out.println(rate);
    }

    @Test
    void parsesJsonFromDummyString() {
        var provider = new CurrencyRateProvider();
        double rate = provider.parseData(predefinedJsonString);
        System.out.println(rate);
    }

    @Test
    void parsesIntoObjectFromDummyString() {
        var provider = new CurrencyRateProvider();
        provider.parseIntoObject(predefinedJsonString);
    }

    @Test
    void f2bTest(){
        var provider = new CurrencyRateProvider();
        String connectionString = provider.getConnectionString("rub", "rub");
        String data = provider.downloadCurrencyData(connectionString);
        double rate = provider.parseData(data);
        assertEquals(1.0, rate);
    }
}