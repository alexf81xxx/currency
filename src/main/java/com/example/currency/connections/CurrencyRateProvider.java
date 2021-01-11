package com.example.currency.connections;

import com.example.currency.models.FxTopLevelObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class CurrencyRateProvider {

    final static String API_KEY = "YOUR_SECRET_KEY";

    public String getConnectionString(String from, String to) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://www.alphavantage.co/query?function=CURRENCY_EXCHANGE_RATE&from_currency=");
        sb.append(from);
        sb.append("&to_currency=");
        sb.append(to);
        sb.append("&apikey=");
        sb.append(API_KEY);

        return sb.toString();
    }

    public String downloadCurrencyData(String url) {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet getRequest = new HttpGet(url);
        getRequest.addHeader("accept", "application/json");
        try {
            HttpResponse response = httpClient.execute(getRequest);
            HttpEntity httpEntity = response.getEntity();
            return EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        } finally {
            httpClient.getConnectionManager().shutdown();
        }
    }

    public double parseData(String input) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            FxTopLevelObject cd = mapper.readValue(input, FxTopLevelObject.class);
            return cd.getFxInternalObject().getExchangeRate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
