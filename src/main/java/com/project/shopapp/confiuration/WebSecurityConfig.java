package com.project.shopapp.confiuration;

import com.project.shopapp.utils.JwtTokenFilterUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtTokenFilterUtil jwtTokenFilterUtil;
    private final CorsConfig corsConfig;
    private final AuthManagerConfig authManagerConfig;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilterUtil, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authManagerConfig)
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfig)
                .build();
    }
}
