package com.example.currency.controllers;


import com.example.currency.models.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.currency.repo.TokenRepo;

@RestController // В пакете Spring MVC
@RequestMapping ("/api/token")

public class TokenController {

    public final TokenRepo tokenRepo;

    @Autowired
    public TokenController(TokenRepo tokenRepo) {
        this.tokenRepo = tokenRepo;
    }


    @PostMapping ("/add")
    public ResponseEntity addToken (@RequestBody Token token){

        Token searchToken = tokenRepo.findByTokenNameAndToken(token.getTokenName(), token.getToken());
        if (searchToken == null){
            System.out.println("Нет такого");
        }

        return ResponseEntity.ok("Token added");
    }
}
