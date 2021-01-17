package com.example.currency.controllers;


import com.example.currency.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.currency.repo.TokenRepo;

import java.time.LocalDate;

@RestController // В пакете Spring MVC
@RequestMapping ("/api/token")

public class TokenController {

    public final TokenRepo tokenRepo;

    @Autowired
    public TokenController(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }


    @PostMapping("/add")
    public ResponseEntity addToken(@RequestBody Token token) throws Exception {

        Token searchToken = tokenRepo.findByTokenNameAndToken(token.getTokenName(), token.getToken());

        if (searchToken == null) {

            token.setCreatedDay(LocalDate.now());
            token.setTokenName(token.getTokenName());
            token.setToken(token.getToken());
            token.setTokenCount(token.getTokenCount() + 1);
            token.setCounter(token.getCounter());

        } else {
            throw new Exception("Token already exist");
        }
        Token tokenSaved = tokenRepo.save(token);
        return ResponseEntity.ok("Token added" + tokenSaved);
    }
}
