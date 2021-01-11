package com.example.currency.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class FxInternalObject {

    @JsonProperty("1. From_Currency Code")
    private String fromCurrencyCode;
    @JsonProperty("2. From_Currency Name")
    private String fromCurrencyName;
    @JsonProperty("3. To_Currency Code")
    private String toCurrencyCode;
    @JsonProperty("4. To_Currency Name")
    private String toCurrencyName;
    @JsonProperty("5. Exchange Rate")
    private double exchangeRate;
//    @JsonProperty("6. Last Refreshed")
//    private LocalDateTime lastRefreshed;
    @JsonProperty("7. Time Zone")
    private String timeZone;
    @JsonProperty("8. Bid Price")
    private double bidPrice;
    @JsonProperty("9. Ask Price")
    private double askPrice;
}
