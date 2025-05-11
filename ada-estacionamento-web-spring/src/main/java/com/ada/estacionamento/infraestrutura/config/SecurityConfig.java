package com.ada.estacionamento.infraestrutura.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {

    private final UserDetailsService userServiceSecurity;

    public SecurityConfig(UserDetailsService userServiceSecurity) {
        this.userServiceSecurity = userServiceSecurity;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)                          // Desabilita CSRF, pois não é necessário para APIs
                .authorizeHttpRequests(authorize -> { authorize
                            .requestMatchers("/usuario/criar/**").permitAll() // Permite acesso ao endpoint de criação de usuário
                            .anyRequest().authenticated();                      // Qualquer outra requisição deve ser autenticada
                })
                .httpBasic(withDefaults())                                      // Habilita autenticação HTTP Basic para APIs
                .build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        // Obtém o AuthenticationManagerBuilder do HttpSecurity
        var authManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        // Configura o UserDetailsService e o PasswordEncoder
        authManagerBuilder
                .userDetailsService(userServiceSecurity)
                .passwordEncoder(passwordEncoder());

        // Constrói e retorna o AuthenticationManager
        return authManagerBuilder.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Objeto Spring que realiza a criptografia de senhas
        return new BCryptPasswordEncoder();
    }
}
