package com.e_sharing.E_sharing.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configurazione di base di Spring Security per l'applicazione e-sharing.
 * <p>
 * - Disabilita la protezione CSRF (adatta ad API REST stateless).
 * - Consente accesso anonimo a /login, /register e /public/**.
 * - Richiede autenticazione per tutti gli altri endpoint.
 * - Disabilita il form di login di default.
 * - Abilita la Basic Auth HTTP (solo per sviluppo/testing).
 * </p>
 */
@Configuration
public class SecurityConfig {

    /**
     * Configura la catena di filtri di sicurezza HTTP.
     * @param http configuratore di sicurezza
     * @return SecurityFilterChain configurata
     * @throws Exception in caso di errori di configurazione
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disabilita CSRF per semplicità su API REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/user/register").permitAll() // endpoint pubblici
                        .anyRequest().authenticated() // tutto il resto richiede autenticazione
                )
                .formLogin(AbstractHttpConfigurer::disable) // Disabilita la form di login standard
                .httpBasic(Customizer.withDefaults()); // Usa HTTP Basic Auth per autenticazione

        return http.build();
    }
}