package com.aliacar.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.aliacar.jwt.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String AUTHENTICATE ="/authenticate";
    public static final String REGISTER="/register";

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        return http
        .csrf(csrf -> csrf.disable()) // CSRF korumasını devre dışı bırak
        .authorizeHttpRequests(auth -> auth
            .requestMatchers(AUTHENTICATE, REGISTER).permitAll() // Belirtilen endpointlere herkese izin ver
            .anyRequest().authenticated() // Diğer tüm istekler kimlik doğrulama gerektirir
        )
        .sessionManagement(session -> 
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // Stateless oturum yönetimi
        )
        .authenticationProvider(authenticationProvider) // Özel authentication provider kullanımı
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class) // JWT filtresini ekle
        .build();
    }
    
}
