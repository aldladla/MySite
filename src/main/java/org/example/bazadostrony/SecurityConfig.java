package org.example.bazadostrony;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authenticationProvider(authenticationProvider());

        http
                .csrf(csrf -> csrf.disable()) // Wyłączamy CSRF – dostosuj do swoich potrzeb
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/register", "/css/**", "/js/**", "/index.html", "/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")                    // Niestandardowy widok logowania
                        .loginProcessingUrl("/login")           // Jawne ustawienie URL, który będzie przetwarzał logowanie
                        .defaultSuccessUrl("/main", true)         // Po poprawnym logowaniu przekierowujemy do /main
                        .failureUrl("/login?error=true")          // W przypadku błędnych danych pozostajemy na /login z parametrem error
                        .permitAll()
                )
                .logout(logout -> logout.permitAll());

        return http.build();
    }
}
