package models;

import java.util.Scanner;

public class Scan {

    // https://www.alphavantage.co/

    public static String getTokenName() {
        System.out.println("Enter token name");
        String tokenName = new Scanner(System.in).nextLine();
        return tokenName;
    }

    public static String getToken() {
        System.out.println("Enter token");
        String token = new Scanner(System.in).nextLine();
        return token;
    }

}
