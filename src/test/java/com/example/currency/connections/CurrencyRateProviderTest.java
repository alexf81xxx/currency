package com.example.currency.connections;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyRateProviderTest {

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
    void f2bTest(){
        var provider = new CurrencyRateProvider();
        String connectionString = provider.getConnectionString("rub", "rub");
        String data = provider.downloadCurrencyData(connectionString);
        double rate = provider.parseData(data);
        assertEquals(1.0, rate);
    }
}