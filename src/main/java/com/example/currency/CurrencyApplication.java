package com.example.currency;

import com.example.currency.connections.CurrencyConnect;
import com.example.currency.models.CurrencyDeserialization;
import com.example.currency.models.CurrencySerialization;
import com.example.currency.models.GetToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.DataInput;

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

        /*Десериализация*/
		ObjectMapper mapper = new ObjectMapper();
		//CurrencyDeserialization currencyDeserialization = mapper.readValue(CurrencyConnect.getCurrencyAPI(), CurrencyDeserialization.class);


    }

}
