package com.example.currency.repo;

import com.example.currency.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepo extends JpaRepository<Token, Long> {

    Token findByTokenNameAndToken (String tokenName, String token);
    Token findByTokenName (String tokenName);

}
