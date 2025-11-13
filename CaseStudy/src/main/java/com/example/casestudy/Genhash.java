package com.example.casestudy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Genhash {
    public static void main(String[] args) {
        BCryptPasswordEncoder enc = new BCryptPasswordEncoder(12); // hoặc 10
        System.out.println(enc.encode("123"));
    }
}