package com.example.currency.models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "token")
public class Token {

    @Transient
    private int tokenCount;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "token_name")
    private String tokenName;

    @Column(name = "token")
    private String token;

    @Column (name = "date")
    private LocalDate createdDay;
}
