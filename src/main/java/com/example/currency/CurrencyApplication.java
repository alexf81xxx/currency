package com.example.currency;

import com.example.currency.connections.CurrencyConnect;
import com.example.currency.models.Scan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CurrencyApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(CurrencyApplication.class, args);



		String tokenName = Scan.getTokenName();
		String token = Scan.getTokenName();


		CurrencyConnect.getCurrencyAPI();
	}

}
