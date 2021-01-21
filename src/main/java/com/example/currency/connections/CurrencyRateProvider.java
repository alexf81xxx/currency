package com.example.currency.connections;

import com.example.currency.models.FxObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Map;

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

    public double parseIntoObject(String input) {

        double fxRate = 0;
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<String, Object> jsonObject = mapper.readerFor(Map.class).readValue(input);
            Map<String, Object> internalContent = (Map<String, Object>) jsonObject.get("Realtime Currency Exchange Rate");
            String fieldLevelObject = (String) internalContent.get("5. Exchange Rate");
            fxRate = Double.parseDouble(fieldLevelObject);
            return fxRate;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return fxRate;
    }

    public double parseDataIntoTree(String input) {
        double fxRate = 0;
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode tree = mapper.readTree(input);
            JsonNode rateNode = tree.findValue("5. Exchange Rate");
            fxRate = rateNode.asDouble();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return fxRate;
    }

    public double parseDataIntoSetupClass(String input) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
        try {
            FxObject cd = mapper.readValue(input, FxObject.class);
            return cd.getExchangeRate();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return 0;
        }
    }
}