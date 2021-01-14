package com.example.currency.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor

@JsonIgnoreProperties (ignoreUnknown = true)

public class CurrencyDeserialization {

    @JsonProperty ("1. From_Currency Code")
    private String fromCurrencyCode;
    @JsonProperty ("2. From_Currency Name")
    private String fromCurrencyName;
    @JsonIgnoreProperties("3. To_Currency Code")
    private String toCurrencyCode;
    @JsonIgnoreProperties("4. To_Currency Name")
    private double toCurrencyName;
    @JsonIgnoreProperties("5. Exchange Rate")
    private double exchangeRate;
    @JsonIgnoreProperties("6. Last Refreshed")
    private LocalDateTime lastRefreshed;
    @JsonProperty("7. Time Zone")
    private String timeZone;
    @JsonIgnoreProperties("8. Bid Price")
    private double bidPrice;
    @JsonIgnoreProperties("9. Ask Price")
    private double askPrice;

}
