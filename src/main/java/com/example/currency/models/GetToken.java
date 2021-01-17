package com.example.currency.models;

import com.example.currency.repo.TokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class GetToken {

    private static TokenRepo tokenRepo;

    @Autowired
    public GetToken(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }

    // Пустой конструктор
    public static String getCurrencyToken(){
        Token getCurrency = tokenRepo.findByTokenName("alphavantage");

        String currencyToken = (String) getCurrency.getToken();
        return currencyToken;
    }

    public static ArrayList<String> getYandex (){

        Token tokenYa = tokenRepo.findByTokenName("yandex");
        ArrayList<String> getYa = new ArrayList<>();
        getYa.add(tokenYa.getToken());
        getYa.add(tokenYa.getCounter());

        return getYa;

    }


}
