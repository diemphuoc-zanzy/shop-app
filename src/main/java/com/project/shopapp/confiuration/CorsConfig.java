package com.project.shopapp.confiuration;

import com.project.shopapp.common.constant.Constant.HEADER;
import com.project.shopapp.common.constant.Constant.METHOD;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class CorsConfig implements Customizer<CorsConfigurer<HttpSecurity>> {

    @Override
    public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // Cho phép origin của FE
        config.setAllowedMethods(METHOD.ALLOWED); // Cho phép tất cả methods
        config.setAllowedHeaders(HEADER.ALLOWED); // Cho phép tất cả headers

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        httpSecurityCorsConfigurer.configurationSource(source);
    }
}
