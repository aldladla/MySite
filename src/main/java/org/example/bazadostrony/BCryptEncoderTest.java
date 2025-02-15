package org.example.bazadostrony;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoderTest {
    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String rawPassword = "root";  // Wprowadź hasło, które chcesz zakodować
        String encodedPassword = bCryptPasswordEncoder.encode(rawPassword);
        System.out.println(encodedPassword);
    }
}

