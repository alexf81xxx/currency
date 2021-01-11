package com.example.currency.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor

public class CurrencyDeserialization {

    private String fromCurrencyCode;
    private String fromCurrencyName;
    private String toCurrencyCode;
    private double toCurrencyName;
    private double exchangeRate;
    private LocalDateTime lastRefreshed;
    private double timeZone;
    private double bidPrice;
    private double askPrice;

}
