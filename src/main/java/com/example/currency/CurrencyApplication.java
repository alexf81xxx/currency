package com.example.currency;

import com.example.currency.connections.CurrencyConnect;
import com.example.currency.connections.YandexConnect;
import com.example.currency.models.CurrencyDeserialization;
import com.example.currency.models.CurrencySerialization;
import com.example.currency.models.GetToken;
import com.example.currency.models.RealtimeCurrencyExchangeRate;
import com.example.currency.queries.YandexQueries;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.DataInput;

@SpringBootApplication
public class CurrencyApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CurrencyApplication.class, args);


        YandexConnect.getYaAPI();

        /*CurrencyConnect.getCurrencyAPI();

		System.out.println("==================");

        CurrencySerialization currencySerialization = new CurrencySerialization("United States Dollar",
				"Russian Ruble", "USD", 74.59, 74.59, 74.59);

        String resultToJson = new ObjectMapper().writeValueAsString(currencySerialization);
		System.out.println(resultToJson);*/

        /*Десериализация*/
		/*ObjectMapper mapper = new ObjectMapper();
        RealtimeCurrencyExchangeRate realtimeCurrencyExchangeRate =
                mapper.readValue(CurrencyConnect.getCurrencyAPI(),
                        RealtimeCurrencyExchangeRate.class);


        System.out.println("==============================");
        System.out.println(realtimeCurrencyExchangeRate);
        System.out.println("За 1 рубль сейчас дают "
               +realtimeCurrencyExchangeRate
               .getCurrencyDeserialization()
               .getBidPrice());*/


    }

}
