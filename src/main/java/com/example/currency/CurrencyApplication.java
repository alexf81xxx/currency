package com.example.currency;

import com.example.currency.connections.CurrencyConnect;
import com.example.currency.models.FxObject;
import com.example.currency.models.CurrencySerialization;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(CurrencyApplication.class, args);

        CurrencyConnect.getCurrencyAPI();

		System.out.println("==================");

        CurrencySerialization currencySerialization = new CurrencySerialization("United States Dollar",
				"Russian Ruble", "USD", 74.59, 74.59, 74.59);

        String resultToJson = new ObjectMapper().writeValueAsString(currencySerialization);
		System.out.println(resultToJson);


		ObjectMapper mapper = new ObjectMapper();

		FxObject currencyDeserialization = mapper.readValue(CurrencyConnect.getCurrencyAPI(), FxObject.class);


    }

}
