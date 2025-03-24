package com.abedkiloo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/api/payment/**")).authenticated()  // Use AntPathRequestMatcher
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()     // H2 Console access
                        .anyRequest().permitAll()
                )
                .httpBasic(withDefaults()) // Enable basic auth
                .csrf(csrf -> csrf.disable()) // Disable CSRF
                .headers(headers -> headers.frameOptions().sameOrigin()); // Allow H2 console frames

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
