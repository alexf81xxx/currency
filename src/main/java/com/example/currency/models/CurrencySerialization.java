package com.example.currency.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CurrencySerialization {

  private String fromCurrency;
  private String toCurrency;
  private String currencyCode;
  private double exchangeRate;
  private double bidPrice;
  private double askPrice;


}
