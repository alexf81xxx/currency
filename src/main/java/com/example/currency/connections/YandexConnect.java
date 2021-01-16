package com.example.currency.connections;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YandexConnect {

    public static java.net.http.HttpResponse<String> connectYandexAPI() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api-metrika.yandex.net/stat/v1/data?preset=tech_platforms&dimensions=ym:s:browser&id=69078571"))
                .setHeader("Authorization", "OAuth AgAAAAA7NVPLAAaxOx2fsvtdl0G7hjPs-UaC4Yo")
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
       int statusCode = response.statusCode();

       if (statusCode != 200){
           throw new IOException("Failed with HTTP error code : " + statusCode);
       }
        System.out.println(response);
        return response;
    }






}
