package com.example.currency.connections;

import com.example.currency.models.GetToken;
import com.example.currency.queries.YandexQueries;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class YandexConnect {

    /*public static java.net.http.HttpResponse<String> connectYandexAPI() throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create("https://api-metrika.yandex.net/stat/v1/data?preset=tech_platforms&dimensions=ym:s:browser&id=69078571"))
                .setHeader("accept", "application/json")
                .setHeader("Authorization", "OAuth AgAAAAA7NVPLAAaxOx2fsvtdl0G7hjPs-UaC4Yo")
                .build();

        java.net.http.HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
       int statusCode = response.statusCode();

       if (statusCode != 200){
           throw new IOException("Failed with HTTP error code : " + statusCode);
       }
        System.out.println(response);
        return response;
    }*/

    public static String getYaAPI(/*String query, String startData, String endtData */) throws Exception
    {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        try
        {
            //Define a HttpGet request; You can choose between HttpPost, HttpDelete or HttpPut also.
            //Choice depends on type of method you will be invoking.
            HttpGet getRequest = new HttpGet(/*"https://api-metrika.yandex.net/stat/v1/data?id=49257631"+"&"+ query+"&"+startData+"&"+endtData*/ "https://api-metrika.yandex.net/stat/v1/data?metrics=ym:s:visits,ym:s:users&id=49257631&date1=2020-01-15&date2=2020-01-16");


            //Set the API media type in http accept header
            getRequest.addHeader("accept", "application/json");
            getRequest.addHeader("Authorization", "OAuth AgAAAAAngk5MAAbSEHilO3iuokb8kJ0xFyKnz8k");


            //Send the request; It will immediately return the response in HttpResponse object
            org.apache.http.HttpResponse response = httpClient.execute(getRequest);

            //verify the valid error code first
            int statusCode = response.getStatusLine().getStatusCode();
            if (statusCode != 200)
            {
                throw new RuntimeException("Failed with HTTP error code : " + statusCode);
            }


            //Now pull back the response object
            HttpEntity httpEntity = response.getEntity();
            String apiOutput = EntityUtils.toString(httpEntity);

            //Lets see what we got from API
            System.out.println(apiOutput); //<user id="10"><firstName>demo</firstName><lastName>user</lastName></user>

            return apiOutput;

        }
        finally
        {
            //Important: Close the connect
            httpClient.getConnectionManager().shutdown();
        }
    }




}
